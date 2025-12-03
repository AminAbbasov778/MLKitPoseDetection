package com.example.posedetection.domain.usecases

import com.example.posedetection.domain.repository.PoseRepository
import javax.inject.Inject

class CloseUseCase  @Inject constructor(
    private val repository: PoseRepository,
) {
   operator fun  invoke() = repository.close()

}

