package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.work.*

class MainActivity : AppCompatActivity() {

    /*
    To send data we need to define a constant key value.
    And to receive data the worker class needs to define a constant key value.
     */
    companion object{
        const val KEY = "KEY_count"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        Implement button to start work manager task.
         */
        val button: Button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
           setOneTimeWorkRequest()
        }
    }


    /*
    Tell work manager to perform the task.

    One time work request example.
     */
    private fun setOneTimeWorkRequest() {
        /*
        Pass some arguments example.
         */
        val data = Data.Builder()
            .putInt(KEY, 125)
            .build()

        /*
        Constraints example for charging.
         */
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        /*
        Get instance of work manager and set one time work request.
         */
        val workManager = WorkManager.getInstance(applicationContext)
        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()
        workManager.enqueue(oneTimeWorkRequest)

        /*
        Get the status update as live data example.
         */
        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(this, Observer {
                /*
                Get the textview from XML to display the text.
                 */
                val textView: TextView = findViewById(R.id.textview)
                textView.text = it.state.name
            })
    }
}