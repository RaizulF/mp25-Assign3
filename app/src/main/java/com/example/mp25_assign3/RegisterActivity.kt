package com.example.mp25_assign3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val viewUsername = findViewById<EditText>(R.id.Edit_Name)
        val viewEmail = findViewById<TextInputEditText>(R.id.Edit_Email)
        val viewPassword = findViewById<TextInputEditText>(R.id.Edit_Password)
        val backToLogin = findViewById<TextView>(R.id.tvLogin)

        backToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        btnRegister.setOnClickListener {
            val username = viewUsername.text.toString()
            val email = viewEmail.text.toString()
            val password = viewPassword.text.toString()

            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                val user = User(username, email, password)
                val intent = Intent(this, LoginActivity::class.java).apply {
                    putExtra("user_data", user)
                }
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
