package net.geeksempire.experimental.demonstration.Process

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay
import java.net.URL

class WorkBackgroundProcess (appContext: Context, workerParams: WorkerParameters) : CoroutineWorker(appContext, workerParams) {

    var processResult = false

    override suspend fun doWork() : Result {

        val imageData = URL("https://pbs.twimg.com/profile_images/1097639932145950722/0SZJGenM_normal.png").readBytes()
        println(">>> ${imageData.size}")

        delay(3000)

        val workOutputData = workDataOf("KEY_IMAGE_DATA" to imageData.slice(IntRange(0, imageData.size/2)).toByteArray())

        val workerResult: Result = Result.success(workOutputData)

        return if (processResult) {
            Result.failure()
        } else {
            workerResult
        }
    }

}