package com.example.posedetection.di.module


import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseDetector
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object PoseModule {

    @Singleton
    @Provides
    fun providePoseDetector(): PoseDetector {
        val options = AccuratePoseDetectorOptions.Builder()
            .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
            .build()
        return PoseDetection.getClient(options)
    }

    @Singleton
    @Provides
    fun provideExecutor(): ExecutorService = Executors.newFixedThreadPool(2)


}