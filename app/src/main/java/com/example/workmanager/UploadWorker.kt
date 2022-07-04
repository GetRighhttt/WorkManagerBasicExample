package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.work.*

/**
 * Example of work manager.
 *
 * Class should always extend worker with context, and params as parameters.
 *
 * doWork() method always overriden also.
 */
class UploadWorker(context: Context,params: WorkerParameters): Worker(context, params) {

    /*
    Write code to execute deferrable task, and pass in KEY from Main. Input data.
     */
    override fun doWork(): Result {

        return try {
            val count = inputData.getInt(MainActivity.KEY, 0)
            for (i in 0 until count) {
                Log.i("MYTAG", "Uploading $i")
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}