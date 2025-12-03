package com.example.posedetection.presentation.util

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseDetector

@OptIn(ExperimentalGetImage::class)
fun processImage(
    imageProxy: ImageProxy,
    detector: PoseDetector,
    callback: (Pose?, Int, Int) -> Unit,
) {
    val mediaImage = imageProxy.image ?: run { imageProxy.close(); return }
    val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

    detector.process(image)
        .addOnSuccessListener { pose ->
            callback(pose, image.width, image.height)
        }
        .addOnCompleteListener {
            imageProxy.close()
        }
}
