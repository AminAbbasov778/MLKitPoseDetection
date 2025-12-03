package com.example.posedetection.domain.repository

import androidx.camera.core.ImageProxy
import com.example.posedetection.domain.model.PoseResult

interface PoseDetectorDataSource {
    fun processImage(imageProxy: ImageProxy): PoseResult
}