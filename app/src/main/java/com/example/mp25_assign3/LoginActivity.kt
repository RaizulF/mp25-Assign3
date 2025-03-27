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

        // Ambil data user dari intent
        registeredUser = intent.getParcelableExtra("user_data", User::class.java)

        btnLogin.setOnClickListener {
            val inputEmail = viewEmail.text.toString()
            val inputPassword = viewPassword.text.toString()

            if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
                showAlert("Error", "Email dan password tidak boleh kosong")
            } else if (inputEmail == registeredUser?.email && inputPassword == registeredUser?.password) {
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("user_data", registeredUser) // Kirim user data ke MainActivity
                }
                startActivity(intent)
                finish()
            } else {
                showAlert("Error", "Email atau password salah")
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
