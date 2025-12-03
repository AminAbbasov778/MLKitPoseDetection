package com.example.posedetection.presentation

sealed class PoseIntent {
    object ToggleCamera : PoseIntent()
}