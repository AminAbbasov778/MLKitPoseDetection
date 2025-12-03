package com.example.posedetection.data

import com.example.posedetection.domain.repository.PoseRepository
import com.google.mlkit.vision.pose.PoseDetector
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class MLKitPoseRepositoryImpl @Inject constructor(
    private val detector: PoseDetector,
    private val executorService: ExecutorService,
) : PoseRepository {

    override fun getPoseDetector(): PoseDetector = detector
    override fun getExecutor(): ExecutorService = executorService

    override fun close() {
        detector.close()
        executorService.shutdown()
    }
}
