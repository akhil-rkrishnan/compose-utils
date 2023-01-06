package lib.composeutils.ui.backgrounds

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Modifier extension for blur circle overlays
 * @param containerColor background color of the composable
 * @param blurRadius radius of the blur circle
 * @param colors list of colors for the radial background
   - Draws blur circle on top center and bottom center of the screen
 */
fun Modifier.drawFadeTopCenterBottomCenter(
    containerColor: Color,
    blurRadius: Dp = 200.dp,
    colors: List<Color>
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


/**
 * Modifier extension for blur circle overlays
 * @param containerColor background color of the composable
 * @param blurRadius radius of the blur circle
 * @param colors list of colors for the radial background
  - Draws blur circle on top start and bottom left of the screen
 */
fun Modifier.drawFadeTopStartBottomLeft(
    containerColor: Color,
    blurRadius: Dp = 200.dp,
    colors: List<Color>
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

/**
 * Modifier extension for blur circle overlays
 * @param containerColor background color of the composable
 * @param blurRadius radius of the blur circle
 * @param colors list of colors for the radial background
  - Draws blur circle on top left and bottom start of the screen
 */
fun Modifier.drawFadeTopLeftBottomStart(
    containerColor: Color,
    blurRadius: Dp = 200.dp,
    colors: List<Color>
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