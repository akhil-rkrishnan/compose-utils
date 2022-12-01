package ak.composeapp

import ak.composeapp.ui.theme.ComposeUtilAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lib.composeutils.ui.CircularProgressBar
import lib.composeutils.ui.LineProgressBar
import lib.composeutils.utils.openActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUtilAppTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 150.dp, vertical = 250.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    LineProgressBar(
                        colors = listOf(Color.Blue, Color.Green, Color.Red, Color.Magenta),
                        shuffleGradient = true,
                        strokeWidth = 30f
                    )
                    CircularProgressBar(
                        colors = listOf(Color.Blue, Color.Black,),
                        shuffleGradient = true,
                        circleStrokeWidth = 20f,
                        circleSize = Size(150f, 150f),
                        initialStartAngle = 10f,
                        initialSweepAngle = 15f,
                        step = 5
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeUtilAppTheme {
        Greeting("Android")
    }
}