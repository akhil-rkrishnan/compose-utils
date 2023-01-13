package ak.composeapp.presentation

import ak.composeapp.model.FreeApiModel
import ak.composeapp.ui.theme.ComposeUtilAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import lib.composeutils.networkUtils.*
import lib.composeutils.utils.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[PresentationViewModel::class.java]
        setContent {
            ComposeUtilAppTheme {
                ConcurrentUpdate(viewModel)
            }
        }
    }


    @Composable
    fun ConcurrentUpdate(viewModel: PresentationViewModel) {
        val scrollState = rememberScrollState()
        LaunchedEffect(key1 = viewModel.initalString, block = {
            viewModel.presentationFlow.collectLatest {
                scrollState.scrollBy(it.toFloat())
            }
        })
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight()
        ) {
            Text(
                text = viewModel.initalString, modifier = Modifier
                    .verticalScroll(scrollState)
            )
        }

    }


    @Composable
    fun SideEffectsCheck() {
        var launchKey by remember {
            mutableStateOf(0)
        }
        var disposableKey by remember {
            mutableStateOf(0)
        }

        LaunchedEffect(key1 = launchKey, block = {
            "Launched Effect executed $launchKey".loge()
        })

        DisposableEffect(key1 = disposableKey, effect = {
            "Disposable Effect executed".loge()
            onDispose {
                "onDispose invoked".loge()
            }
        })

        SideEffect {
            "Side effect executed".loge()
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable {
                //  launchKey++
                //  disposableKey++
            }) {
            Text(text = "Click me!")
        }


    }

    fun apiCall() {
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
    }
}


interface ApiPath {
    @GET("entries")
    suspend fun getAllData(): FreeApiModel
}






