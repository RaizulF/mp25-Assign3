package com.example.mp25_assign3

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var loginUser: User? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val welcome = findViewById<TextView>(R.id.view_Welcome)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        loginUser = intent.getParcelableExtra("user_data", User::class.java)
        welcome.text = getString(R.string.welcome_message, loginUser?.username ?: "User")

        btnLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply {
                putExtra("user_data", loginUser)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(intent)
            finish()
        }
    }
}
