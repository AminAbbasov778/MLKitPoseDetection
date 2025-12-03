package com.example.posedetection.domain.repository

import com.google.mlkit.vision.pose.PoseDetector
import java.util.concurrent.ExecutorService

interface PoseRepository {
    fun getPoseDetector(): PoseDetector
    fun getExecutor(): ExecutorService
    fun close()
}