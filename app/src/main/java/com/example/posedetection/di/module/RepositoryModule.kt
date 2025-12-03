package com.example.posedetection.di.module

import com.example.posedetection.data.MLKitPoseRepositoryImpl
import com.example.posedetection.domain.repository.PoseRepository
import com.google.mlkit.vision.pose.PoseDetector
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.ExecutorService
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePoseRepository(
        detector: PoseDetector, executorService: ExecutorService,
    ): PoseRepository {
        return MLKitPoseRepositoryImpl(detector, executorService)
    }

}