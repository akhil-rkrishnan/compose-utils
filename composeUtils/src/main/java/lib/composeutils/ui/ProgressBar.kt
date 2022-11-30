package lib.composeutils.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.toSize
import timber.log.Timber

private const val TAG = "ProgressBar"

@Composable
fun LineProgressbar(
    modifier: Modifier = Modifier,
    lineWidth: Float = 10f,
    shuffleGradient: Boolean = false,
    colors: List<Color>
) {

    if (colors.isEmpty() || colors.size < 2) {
        Timber.e("LineProgressbar: color list should have minimum of 2 values")
        return
    }

    var colorList by remember {
        mutableStateOf(colors)
    }

    var boxCordinates by remember {
        mutableStateOf(Size.Unspecified)
    }


    var lineSlider by remember {
        mutableStateOf(LineSlide.LEFT_START_TO_RIGHT)
    }

    Box(modifier = modifier
        .fillMaxSize()
        .onGloballyPositioned {
            boxCordinates = it.size.toSize()
        }) {

        var endX by remember {
            mutableStateOf(0f)
        }

        var startX by remember {
            mutableStateOf(0f)
        }

        LaunchedEffect(key1 = startX, block = {
            if (lineSlider == LineSlide.LEFT_START_TO_RIGHT) {
                if (endX > boxCordinates.width) {
                    if (startX > boxCordinates.width) {
                        lineSlider = LineSlide.LEFT_START_TO_LEFT
                        endX = boxCordinates.width
                        if (shuffleGradient)
                            colorList = colorList.shuffled()
                    } else {
                        startX += 7
                    }
                } else {
                    startX += 5; endX += 10;
                }
            }
        })

        LaunchedEffect(key1 = endX, block = {
            if (lineSlider == LineSlide.LEFT_START_TO_LEFT) {
                if (startX <= 0f) {
                    if (endX <= 0f) {
                        lineSlider = LineSlide.LEFT_START_TO_RIGHT
                        startX = 0f
                        if (shuffleGradient)
                            colorList = colorList.shuffled()
                    } else {
                        endX -= 7
                    }
                } else {
                    endX -= 5; startX -= 10
                }
            }
        })


        Canvas(modifier = Modifier
            .fillMaxWidth(), onDraw = {
            drawLine(
                brush = Brush.linearGradient(
                    colors = colorList,
                ),
                start = Offset(x = startX, y = 0f),
                end = Offset(x = endX, y = 0f),
                strokeWidth = lineWidth, cap = StrokeCap.Round
            )

        })
    }
}

enum class LineSlide {
    LEFT_START_TO_RIGHT, LEFT_START_TO_LEFT
}