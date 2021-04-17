package com.example.timerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    private var timer: CountDownTimer? = null
    var millis = 0L
    lateinit var timerText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        timerText = findViewById(R.id.timer2)
        millis = intent.getLongExtra("Timer", 0)
    }


    override fun onStart() {
        super.onStart()
        timer = object: CountDownTimer(millis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerText.text = "Timer: ${millisUntilFinished / 1000}"
                millis = millisUntilFinished
            }

            override fun onFinish() {
                timerText.text = "Timer: Finished"
            }
        }.start()
    }

    override fun onStop() {
        super.onStop()
        timer?.cancel()
    }
}