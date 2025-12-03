package com.example.posedetection.presentation.ui

import androidx.lifecycle.ViewModel
import com.example.posedetection.domain.usecases.CloseUseCase
import com.example.posedetection.domain.usecases.GetDetectorUseCase
import com.example.posedetection.domain.usecases.GetExecutorUseCase
import com.example.posedetection.presentation.PoseIntent
import com.example.posedetection.presentation.PoseState
import com.google.mlkit.vision.pose.Pose
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PoseViewModel @Inject constructor(
    private val getDetectorUseCase: GetDetectorUseCase,
    private val getExecutorUse: GetExecutorUseCase,
    private val closeUseCase: CloseUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PoseState())
    val state: StateFlow<PoseState> = _state

    fun handleIntent(intent: PoseIntent) {
        when (intent) {
            is PoseIntent.ToggleCamera -> {
                _state.value = _state.value.copy(isFrontCamera = !_state.value.isFrontCamera)
            }
        }
    }

    fun updatePose(pose: Pose?, width: Int, height: Int) {
        _state.value = _state.value.copy(
            pose = pose,
            imageWidth = width,
            imageHeight = height
        )
    }

    fun getDetector() = getDetectorUseCase()
    fun getExecutor() = getExecutorUse()

    override fun onCleared() {
        super.onCleared()
        closeUseCase()
    }
}
