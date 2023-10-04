package com.lauwba.wisatakita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.lauwba.wisatakita.databinding.ActivitySpashScreenBinding

class SpashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpashScreenBinding
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpashScreenBinding.inflate(layoutInflater)

        handler=Handler(Looper.getMainLooper())
        handler.postDelayed({
            finish()
            startActivity(Intent(this@SpashScreenActivity, MainActivity::class.java))
        }, 3000)
        setContentView(binding.root)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacksAndMessages(null)
    }
}