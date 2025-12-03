package com.example.posedetection.presentation.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.google.mlkit.vision.pose.Pose

fun DrawScope.drawHandBox(
    pose: Pose,
    wrist: Int,
    index: Int,
    pinky: Int,
    scale: Float,
    offsetX: Float,
    offsetY: Float,
    isFrontCamera: Boolean,
) {

    fun Float.toScreenX(viewW: Float): Float {
        val x = this * scale + offsetX
        return if (isFrontCamera) viewW - x else x
    }

    fun Float.toScreenY(): Float = this * scale + offsetY

    val wristL = pose.getPoseLandmark(wrist) ?: return
    val indexL = pose.getPoseLandmark(index) ?: return
    val pinkyL = pose.getPoseLandmark(pinky) ?: return

    val centerX = (wristL.position.x + indexL.position.x + pinkyL.position.x) / 3f
    val centerY = (wristL.position.y + indexL.position.y + pinkyL.position.y) / 3f

    val cx = centerX.toScreenX(size.width)
    val cy = centerY.toScreenY()

    val handWidth = kotlin.math.sqrt(
        (indexL.position.x - pinkyL.position.x).let { it * it } +
                (indexL.position.y - pinkyL.position.y).let { it * it }
    )

    val handHeight = kotlin.math.sqrt(
        (wristL.position.x - indexL.position.x).let { it * it } +
                (wristL.position.y - indexL.position.y).let { it * it }
    )

    val handSize = maxOf(handWidth, handHeight) * scale
    val boxSize = handSize * 2f       // əl ölçüsündən böyük

    val topLeft = Offset(
        cx - boxSize / 2f,
        cy - boxSize / 2f
    )

    drawRect(
        color = Color.Red.copy(alpha = 0.5f),
        topLeft = topLeft,
        size = Size(boxSize, boxSize),
        style = Stroke(width = 4f)
    )
}
