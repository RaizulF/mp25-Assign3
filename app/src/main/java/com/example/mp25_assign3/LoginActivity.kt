package com.example.mp25_assign3

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    private var registeredUser: User? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val viewEmail = findViewById<TextInputEditText>(R.id.Edit_Email)
        val viewPassword = findViewById<TextInputEditText>(R.id.Edit_Password)
        val goToRegister = findViewById<TextView>(R.id.tvRegister)

        registeredUser = intent.getParcelableExtra("user_data", User::class.java)

        btnLogin.setOnClickListener {
            val inputEmail = viewEmail.text.toString().trim()
            val inputPassword = viewPassword.text.toString().trim()

            when {
                inputEmail.isEmpty() && inputPassword.isEmpty() -> {
                    showAlert("Error", "Semua field harus diisi")
                }

                inputEmail.isEmpty() -> {
                    showAlert("Error", "Email tidak boleh kosong")
                }

                inputPassword.isEmpty() -> {
                    showAlert("Error", "Password tidak boleh kosong")
                }

                !android.util.Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches() -> {
                    showAlert("Error", "Format email tidak valid")
                }

                registeredUser == null -> {
                    showAlert("Error", "Akun belum terdaftar. Silakan daftar terlebih dahulu.")
                }

                inputEmail != registeredUser?.email -> {
                    showAlert("Error", "Email tidak ditemukan. Silakan periksa kembali.")
                }

                inputPassword != registeredUser?.password -> {
                    showAlert("Error", "Password salah. Silakan coba lagi.")
                }

                else -> {
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra("user_data", registeredUser)
                    }
                    startActivity(intent)
                    finish()
                }
            }
        }

        goToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
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