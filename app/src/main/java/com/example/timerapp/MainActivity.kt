package com.example.timerapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var timer: CountDownTimer? = null
    private var millis: Long = 0
    private lateinit var timerText: TextView
    private var startIsClicked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val timerText = findViewById<TextView>(R.id.timer)
        val startTimer = findViewById<Button>(R.id.start)
        val nextPage = findViewById<Button>(R.id.next)
        timer = object: CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerText.text = "Timer: ${millisUntilFinished/1000}"
                millis = millisUntilFinished-1
            }

            override fun onFinish() {
                timerText.text = "Timer: Finished"
            }
        }

        startTimer.setOnClickListener {
            timer?.start()
            startIsClicked = true
        }

        nextPage.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java).apply {
                putExtra("Timer", millis)
            }
            startActivity(intent)
        }
    }


    override fun onStart() {
        super.onStart()
        if(startIsClicked){
            timer = object : CountDownTimer(millis, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timerText.text = "Timer: ${millisUntilFinished / 1000}"
                    millis = millisUntilFinished
                }

                override fun onFinish() {
                    timerText.text = "Timer: Finished"
                }
            }.start()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        timer?.cancel()
    }
}