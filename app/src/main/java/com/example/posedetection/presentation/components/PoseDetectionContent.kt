package com.example.posedetection.presentation.components

import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.posedetection.presentation.PoseIntent
import com.example.posedetection.presentation.PoseState
import com.example.posedetection.presentation.ui.PoseViewModel
import com.example.posedetection.presentation.util.startCamera
import com.google.mlkit.vision.pose.PoseLandmark
import kotlin.math.min

@Composable
fun PoseDetectionContent(viewModel: PoseViewModel) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by viewModel.state.collectAsState()

    val previewView = remember { PreviewView(context) }

    LaunchedEffect(state.isFrontCamera) {
        startCamera(
            context,
            lifecycleOwner,
            previewView,
            state.isFrontCamera,
            viewModel.getExecutor(),
            viewModel.getDetector()
        ) { pose, w, h ->
            viewModel.updatePose(pose, w, h)
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        AndroidView(
            factory = { previewView.apply { scaleType = PreviewView.ScaleType.FIT_CENTER } },
            modifier = Modifier.fillMaxSize()
        )



        Canvas(modifier = Modifier.fillMaxSize()) {

            val pose = state.pose ?: return@Canvas

            val viewW = size.width
            val viewH = size.height
            val imgW = state.imageWidth.toFloat()
            val imgH = state.imageHeight.toFloat()
            if (imgW <= 0 || imgH <= 0) return@Canvas

            val scale = min(viewW / imgW, viewH / imgH)
            val offsetX = (viewW - imgW * scale) / 2
            val offsetY = (viewH - imgH * scale) / 2

            val toScreenX: (Float) -> Float = { x ->
                val v = x * scale + offsetX
                if (state.isFrontCamera) viewW - v else v
            }
            val toScreenY: (Float) -> Float = { y -> y * scale + offsetY }

            drawSkeleton(pose, toScreenX, toScreenY)


            drawLandmarks(pose, toScreenX, toScreenY)





            drawHandBox(
                pose, PoseLandmark.LEFT_WRIST, PoseLandmark.LEFT_INDEX, PoseLandmark.LEFT_PINKY,
                scale, offsetX, offsetY, state.isFrontCamera
            )


            drawHandBox(
                pose,
                PoseLandmark.RIGHT_WRIST,
                PoseLandmark.RIGHT_INDEX,
                PoseLandmark.RIGHT_PINKY,
                scale,
                offsetX,
                offsetY,
                state.isFrontCamera
            )
        }


        Button(
            onClick = { viewModel.handleIntent(PoseIntent.ToggleCamera) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text(if (state.isFrontCamera) "Arxa kamera" else "Ön kamera")
        }
    }


}
