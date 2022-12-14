package lib.composeutils.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.toSize
import lib.composeutils.exception.ColorListViolation

private const val TAG = "ProgressBar"

/**
 * Method to draw line progress bar
 *
 * @param modifier   Modifier for the box
 * @param strokeWidth  line width
 * @param shuffleGradient   if the gradient colors needs to be shuffled set it as true, then it will shuffle the color list
 * @param colors   list of colors need to render the line.
 */

@Composable
fun LineProgressBar(
    modifier: Modifier = Modifier,
    strokeWidth: Float = 10f,
    shuffleGradient: Boolean = false,
    colors: List<Color>
) {

    if (colors.isEmpty() || colors.size < 2) {
        throw ColorListViolation(listSize = 0)
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


        Canvas(modifier = Modifier.fillMaxWidth(), onDraw = {
            drawLine(
                brush = Brush.linearGradient(
                    colors = colorList,
                ),
                start = Offset(x = startX, y = 0f),
                end = Offset(x = endX, y = 0f),
                strokeWidth = strokeWidth, cap = StrokeCap.Round
            )

        })
    }
}

/**
 *  Method to draw circular progress bar - alpha version
 *  @param colors   Color for the progress bar
 */
@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    colors: List<Color>,
    shuffleGradient: Boolean = false,
    circleStrokeWidth: Float = 10f,
    circleSize: Size = Size(100f, 100f),
    initialStartAngle: Float = 0f,
    initialSweepAngle: Float = 0f,
    step: Int = 2
) {

    if (colors.isEmpty() || colors.size < 2)
        throw ColorListViolation(listSize = colors.size)

    var colorList by remember {
        mutableStateOf(colors)
    }
    var initialStart by remember {
        mutableStateOf(initialStartAngle)
    }

    var sweepStart by remember {
        mutableStateOf(initialSweepAngle)
    }

    val toAngle by animateFloatAsState(
        targetValue = initialStart
    )
    LaunchedEffect(key1 = initialStart, block = {
        initialStart += step
        sweepStart += step

        if (initialStart == 360f) {
            sweepStart = -90f
            initialStart = -90f
            if (shuffleGradient)
                colorList = colorList.shuffled()
        }
    })
    Box(modifier = modifier) {
        Canvas(modifier = Modifier
            .align(Alignment.Center), onDraw = {
            drawArc(
                brush = Brush.verticalGradient(
                    colors = colorList,
                    startY = 0f,
                    endY = 120f
                ),
                startAngle = toAngle,
                sweepAngle = sweepStart,
                useCenter = false,
                size = circleSize,
                style = Stroke(width = circleStrokeWidth, miter = 0f, cap = StrokeCap.Round),
            )
        })
    }
}


enum class LineSlide {
    LEFT_START_TO_RIGHT, LEFT_START_TO_LEFT
}