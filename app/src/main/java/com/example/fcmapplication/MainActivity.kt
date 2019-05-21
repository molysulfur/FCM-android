package com.example.fcmapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_token.setOnClickListener {
            FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w("Token", "getInstanceId failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new Instance ID token
                    val token = task.result?.token

                    // Log and toast
                    val msg = token
                    Log.d("Token", msg)
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                })
        }

        btn_topic.setOnClickListener {
            FirebaseMessaging.getInstance().subscribeToTopic("global")
                .addOnCompleteListener { task ->
                    Log.d("topic", task.toString())
                    Toast.makeText(this, "topic", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
