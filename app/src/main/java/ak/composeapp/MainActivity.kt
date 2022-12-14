package ak.composeapp

import ak.composeapp.model.FreeApiModel
import ak.composeapp.ui.theme.ComposeUtilAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import lib.composeutils.networkUtils.*
import lib.composeutils.ui.UiMessage
import lib.composeutils.ui.showToast
import lib.composeutils.utils.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.publicapis.org/")
            .client(
                OkHttpClient
                    .Builder().readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .build()
            )
            .build()
        val retrofitApi = retrofit.create(ApiPath::class.java)
        var response: ApiResult<FreeApiModel>
        lifecycleScope.launch {
            response = initApiCall {
                ApiResult.Success(retrofitApi.getAllData())
            }

            response.ifSuccess {
                // do the operations after api success
            }
            response.ifFailed { code, message ->
                // do the operations on api failed
            }
        }
        setContent {
            ComposeUtilAppTheme {
                val uiMessage = UiMessage.FromString("This is dynamic string")
                val uiMessage2 = UiMessage.FromStringRes(R.string.app_name)
                showToast(uiMessage2)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .drawFadeTopLeftBottomStart(Color.Black)
                )
            }
        }
    }
}

interface ApiPath {
    @GET("entries")
    suspend fun getAllData(): FreeApiModel
}


fun Modifier.drawFadeTopCenterBottomCenter(
    containerColor: Color,
    blurRadius: Dp = 200.dp,
    colors: List<Color> = listOf(
        Color(0xFF492E82),
        Color(0xFF272C44),
        Color.Transparent
    )
): Modifier = drawBehind {
    val radius = blurRadius.toPx()
    drawRect(color = containerColor)
    val topCenterOffset =
        Offset(x = size.width / 2, y = center.y - (size.height / 1.8f))
    drawCircle(
        brush = Brush.radialGradient(
            colors,
            center = topCenterOffset
        ),
        radius = radius,
        center = topCenterOffset
    )

    val bottomCenterOffset =
        Offset(x = size.width / 2, y = center.y + (size.height / 1.8f))
    drawCircle(
        brush = Brush.radialGradient(
            colors,
            center = bottomCenterOffset
        ),
        radius = radius,
        center = bottomCenterOffset
    )
}

fun Modifier.drawFadeTopStartBottomLeft(
    containerColor: Color,
    blurRadius: Dp = 200.dp,
    colors: List<Color> = listOf(
        Color(0xFF492E82),
        Color(0xFF272C44),
        Color.Transparent
    )
): Modifier = drawBehind {
    val radius = blurRadius.toPx()
    drawRect(color = containerColor)
    val topLeftCircleOffset =
        Offset(x = (-(radius / 3)), y = center.y - (size.height / 4))
    drawCircle(
        brush = Brush.radialGradient(
            colors,
            center = topLeftCircleOffset
        ),
        radius = radius,
        center = topLeftCircleOffset
    )

    val bottomRightCircleOffset =
        Offset(x = size.width + (radius / 3), y = center.y + (size.height / 4))
    drawCircle(
        brush = Brush.radialGradient(
            colors,
            center = bottomRightCircleOffset
        ),
        radius = radius,
        center = bottomRightCircleOffset
    )
}

fun Modifier.drawFadeTopLeftBottomStart(
    containerColor: Color,
    blurRadius: Dp = 200.dp,
    colors: List<Color> = listOf(
        Color(0xFF492E82),
        Color(0xFF272C44),
        Color.Transparent
    )
): Modifier = drawBehind {
    val radius = blurRadius.toPx()
    drawRect(color = containerColor)
    val topRightCircleOffset =
        Offset(x = size.width + (radius / 3), y = center.y - (size.height / 4))
    drawCircle(
        brush = Brush.radialGradient(
            colors,
            center = topRightCircleOffset
        ),
        radius = radius,
        center = topRightCircleOffset
    )

    val bottomLeftCircleOffset =
        Offset(x = -(radius / 3), y = center.y + (size.height / 4))
    drawCircle(
        brush = Brush.radialGradient(
            listOf(
                Color(0xFF492E82),
                Color(0xFF272C44),
                Color.Transparent
            ),
            center = bottomLeftCircleOffset
        ),
        radius = radius,
        center = bottomLeftCircleOffset
    )
}




