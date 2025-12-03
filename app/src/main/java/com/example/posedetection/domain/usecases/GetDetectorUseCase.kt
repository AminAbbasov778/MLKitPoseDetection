package com.example.posedetection.domain.usecases

import com.example.posedetection.domain.repository.PoseRepository
import javax.inject.Inject

class GetDetectorUseCase @Inject constructor(
    private val repository: PoseRepository,
) {
   operator fun invoke() = repository.getPoseDetector()

}
