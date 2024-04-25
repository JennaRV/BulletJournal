// AppBackground.kt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.drawscope.drawBehind
import androidx.compose.foundation.layout.BoxWithConstraints


import androidx.compose.ui.geometry.Offset


@Composable
fun AppBackgroundFront(content: @Composable () -> Unit) {
    BoxWithConstraints {
        val maxWidth = constraints.maxWidth.toFloat()
        val maxHeight = constraints.maxHeight.toFloat()

        // Prepare your dynamic calculations here
        val circleRadius = maxWidth * 0.6f
        val smallCircleRadius = maxWidth * 0.15f
        val circleCenter = Offset(maxWidth / 2, maxHeight / 6)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(red = 100, green = 110, blue = 245))
                .drawBehind {
                    // Drawing circles with calculated sizes and positions
                    drawCircle(
                        color = Color(red = 214, green = 66, blue = 105),
                        radius = circleRadius,
                        center = circleCenter
                    )

                }
        ) {
            content()
        }
    }
}
@Composable
fun AppBackgroundGeneral(content: @Composable () -> Unit) {
    BoxWithConstraints {
        val maxWidth = constraints.maxWidth.toFloat()
        val maxHeight = constraints.maxHeight.toFloat()

        // Prepare your dynamic calculations here
        val circleRadius = maxWidth * 0.6f
        val smallCircleRadius = maxWidth * 0.15f
        val circleCenter = Offset(maxWidth / 2, maxHeight / 6)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(red = 100, green = 110, blue = 245))
                .drawBehind {
                    // Drawing circles with calculated sizes and positions
                    drawCircle(
                        color = Color(red = 214, green = 66, blue = 105),
                        radius = circleRadius,
                        center = circleCenter
                    )
                }
        ) {
            content()
        }
    }
}