package com.example.posedetection.presentation.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseLandmark

fun DrawScope.drawSkeleton(
    pose: Pose,
    toScreenX: (Float) -> Float,
    toScreenY: (Float) -> Float,
) {
    val lines = listOf(
        PoseLandmark.LEFT_SHOULDER to PoseLandmark.RIGHT_SHOULDER,
        PoseLandmark.LEFT_SHOULDER to PoseLandmark.LEFT_ELBOW,
        PoseLandmark.LEFT_ELBOW to PoseLandmark.LEFT_WRIST,
        PoseLandmark.LEFT_WRIST to PoseLandmark.LEFT_THUMB,
        PoseLandmark.LEFT_WRIST to PoseLandmark.LEFT_INDEX,
        PoseLandmark.LEFT_WRIST to PoseLandmark.LEFT_PINKY,
        PoseLandmark.RIGHT_SHOULDER to PoseLandmark.RIGHT_ELBOW,
        PoseLandmark.RIGHT_ELBOW to PoseLandmark.RIGHT_WRIST,
        PoseLandmark.RIGHT_WRIST to PoseLandmark.RIGHT_THUMB,
        PoseLandmark.RIGHT_WRIST to PoseLandmark.RIGHT_INDEX,
        PoseLandmark.RIGHT_WRIST to PoseLandmark.RIGHT_PINKY,
        PoseLandmark.LEFT_SHOULDER to PoseLandmark.LEFT_HIP,
        PoseLandmark.RIGHT_SHOULDER to PoseLandmark.RIGHT_HIP,
        PoseLandmark.LEFT_HIP to PoseLandmark.RIGHT_HIP,
        PoseLandmark.LEFT_HIP to PoseLandmark.LEFT_KNEE,
        PoseLandmark.LEFT_KNEE to PoseLandmark.LEFT_ANKLE,
        PoseLandmark.LEFT_ANKLE to PoseLandmark.LEFT_HEEL,
        PoseLandmark.LEFT_HEEL to PoseLandmark.LEFT_FOOT_INDEX,
        PoseLandmark.LEFT_ANKLE to PoseLandmark.LEFT_FOOT_INDEX,
        PoseLandmark.RIGHT_HIP to PoseLandmark.RIGHT_KNEE,
        PoseLandmark.RIGHT_KNEE to PoseLandmark.RIGHT_ANKLE,
        PoseLandmark.RIGHT_ANKLE to PoseLandmark.RIGHT_HEEL,
        PoseLandmark.RIGHT_HEEL to PoseLandmark.RIGHT_FOOT_INDEX,
        PoseLandmark.RIGHT_ANKLE to PoseLandmark.RIGHT_FOOT_INDEX,
    )

    lines.forEach { (a, b) ->
        val start = pose.getPoseLandmark(a) ?: return@forEach
        val end = pose.getPoseLandmark(b) ?: return@forEach

        drawLine(
            color = Color.White,
            start = Offset(toScreenX(start.position.x), toScreenY(start.position.y)),
            end = Offset(toScreenX(end.position.x), toScreenY(end.position.y)),
            strokeWidth = 5f
        )
    }
}
