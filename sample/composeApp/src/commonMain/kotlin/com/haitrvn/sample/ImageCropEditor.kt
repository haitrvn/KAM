import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.*

@Composable
fun ImageCropper(
    painter: BitmapPainter,
    aspectRatio: Float,
    onCropComplete: (BitmapPainter) -> Unit
) {
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var imageSize by remember { mutableStateOf(Size.Zero) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, rotationChange ->
                            scale *= zoom
                            rotation += rotationChange
                            offset += pan
                        }
                    }
            ) {
                // Save image size on first draw
                if (imageSize == Size.Zero) {
                    imageSize = Size(
                        painter.intrinsicSize.width,
                        painter.intrinsicSize.height
                    )
                }

                // Draw background
                drawRect(Color.Black.copy(alpha = 0.7f))

                // Calculate crop rect based on aspect ratio
                val cropRectSize = calculateCropRectSize(size, aspectRatio)
                val cropRect = Rect(
                    center = center,
                    size = cropRectSize
                )

                // Draw the image
                rotate(rotation) {
                    with(painter) {
                        draw(
                            size = imageSize * scale,
                            alpha = 1f,
                            colorFilter = null,
                        )
                    }
                }

                // Draw crop overlay
                drawCropOverlay(cropRect)
            }
        }

        Button(
            onClick = {
                // Create cropped bitmap
                val croppedBitmap = createCroppedBitmap(
                    painter,
                    scale,
                    rotation,
                    offset,
                    imageSize
                )
                onCropComplete(BitmapPainter(croppedBitmap))
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Confirm")
        }
    }
}

private fun calculateCropRectSize(canvasSize: Size, aspectRatio: Float): Size {
    val width = minOf(canvasSize.width, canvasSize.height * aspectRatio)
    val height = width / aspectRatio
    return Size(width, height)
}

private fun DrawScope.drawCropOverlay(cropRect: Rect) {
    // Draw semi-transparent overlay
    drawRect(
        color = Color.Black.copy(alpha = 0.5f),
        size = size
    )

    // Clear crop area
    drawRect(
        color = Color.Transparent,
        topLeft = cropRect.topLeft,
        size = cropRect.size,
        blendMode = BlendMode.Clear
    )

    // Draw crop border
    drawRect(
        color = Color.White,
        topLeft = cropRect.topLeft,
        size = cropRect.size,
        style = Stroke(width = 2f)
    )
}

private fun createCroppedBitmap(
    source: ImageBitmap,
    scale: Float,
    rotation: Float,
    offset: Offset,
    imageSize: Size
): ImageBitmap {
    // Create new bitmap for cropped image
    val croppedBitmap = ImageBitmap(
        width = (imageSize.width * scale).toInt(),
        height = (imageSize.height * scale).toInt()
    )

    // Create Canvas and apply transformations
    val canvas = Canvas(croppedBitmap)
    canvas.save()
    canvas.translate(offset.x, offset.y)
    canvas.rotate(rotation)
    canvas.scale(scale, scale)

    // Draw source bitmap
    canvas.drawImageRect(
        source,
        IntOffset.Zero,
        imageSize
    )

    canvas.restore()

    return croppedBitmap
}