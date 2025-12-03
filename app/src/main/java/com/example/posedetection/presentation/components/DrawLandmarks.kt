package com.example.posedetection.presentation.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.google.mlkit.vision.pose.Pose

fun DrawScope.drawLandmarks(
    pose: Pose,
    toScreenX: (Float) -> Float,
    toScreenY: (Float) -> Float,
) {
    pose.allPoseLandmarks.forEach { lm ->
        drawCircle(
            color = Color.Blue,
            radius = 7f,
            center = Offset(toScreenX(lm.position.x), toScreenY(lm.position.y)),

            )
        drawCircle(
            color = Color.White,
            radius = 7f,

            center = Offset(toScreenX(lm.position.x), toScreenY(lm.position.y)),
            style = Stroke(width = 3f)

        )
    }
}
