package com.example.mp25_assign3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    private var registUser: String? = null
    private var registEmail: String? = null
    private var registPw: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val viewEmail = findViewById<TextInputEditText>(R.id.Edit_Email)
        val viewPassword = findViewById<TextInputEditText>(R.id.Edit_Password)
        val goToRegister = findViewById<TextView>(R.id.tvRegister)

        registUser = intent.getStringExtra("username")
        registEmail = intent.getStringExtra("email")
        registPw = intent.getStringExtra("password")

        btnLogin.setOnClickListener {
            val email = viewEmail.text.toString()
            val password = viewPassword.text.toString()

            if (email.isEmpty() && password.isEmpty()) {
                showAlert("Error", "Email dan password tidak boleh kosong")

            } else if (email == registEmail && password == registPw){
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("username", registUser)
                startActivity(intent)
                finish()
            } else {
                showAlert("Error", "Email atau password salah")
            }
        }

        goToRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showAlert(title: String, message: String)  {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}