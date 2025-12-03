package com.example.posedetection.domain.model

import com.google.mlkit.vision.pose.Pose

data class PoseResult(
    val pose: Pose?,
    val width: Int,
    val height: Int,
)