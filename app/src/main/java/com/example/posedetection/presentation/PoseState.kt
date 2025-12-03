package com.example.posedetection.presentation

import com.google.mlkit.vision.pose.Pose

data class PoseState(
    val pose: Pose? = null,
    val isFrontCamera: Boolean = true,
    val imageWidth: Int = 0,
    val imageHeight: Int = 0,
)