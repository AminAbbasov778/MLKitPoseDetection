package com.example.posedetection.data.ml

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import com.example.posedetection.domain.model.PoseResult
import com.example.posedetection.domain.repository.PoseDetectorDataSource
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.PoseDetector
import javax.inject.Inject

class MlKitPoseDetector @Inject constructor(
    private val detector: PoseDetector,
) : PoseDetectorDataSource {
    @androidx.annotation.OptIn(ExperimentalGetImage::class)
    @OptIn(ExperimentalGetImage::class)
    override fun processImage(imageProxy: ImageProxy): PoseResult {
        val mediaImage = imageProxy.image ?: return PoseResult(null, 0, 0)

        val image = InputImage.fromMediaImage(
            mediaImage,
            imageProxy.imageInfo.rotationDegrees
        )

        var poseData = PoseResult(null, image.width, image.height)

        detector.process(image)
            .addOnSuccessListener { pose ->
                poseData = PoseResult(pose, image.width, image.height)
            }

        return poseData
    }
}