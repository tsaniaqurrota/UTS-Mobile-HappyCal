package com.example.happycal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.happycal.databinding.ActivityMainBinding
import com.example.happycal.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnStarted.setOnClickListener {
                val intent = Intent(this@WelcomeActivity, GetStartedActivity::class.java)
                startActivity(intent)
            }
        }
    }
}