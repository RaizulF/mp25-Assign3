package com.example.mp25_assign3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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
            val username = viewUsername.text.toString().trim()
            val email = viewEmail.text.toString().trim()
            val password = viewPassword.text.toString().trim()

            when {
                username.isEmpty() && email.isEmpty() && password.isEmpty() -> {
                    showAlert("Error", "Semua field harus diisi")
                }

                username.isEmpty() -> {
                    showAlert("Error", "Username tidak boleh kosong")
                }

                email.isEmpty() -> {
                    showAlert("Error", "Email tidak boleh kosong")
                }

                password.isEmpty() -> {
                    showAlert("Error", "Password tidak boleh kosong")
                }

                !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    showAlert("Error", "Format email tidak valid")
                }

                password.length < 8 || password.length > 12 -> {
                    showAlert("Error", "Password minimal 8 karakter dan maksimal 12 karakter")
                }

                else -> {
                    val user = User(username, email, password)
                    val intent = Intent(this, LoginActivity::class.java).apply {
                        putExtra("user_data", user)
                    }
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun showAlert(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}