package com.example.duannoithat

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val btnGetStarted = findViewById<AppCompatButton>(R.id.btnGetStarted)

        btnGetStarted.setOnClickListener {
            val intent = android.content.Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }
}