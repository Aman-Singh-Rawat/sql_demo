package com.example.sqlitedemofour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private var loginEmail: EditText? = null
    private var loginPassword: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginEmail = findViewById(R.id.loginEmail)
        loginPassword = findViewById(R.id.loginPassword)

    }
    fun loginButton(view: View){
        val email = loginEmail?.text.toString()
        val password = loginPassword?.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()){
            val databaseCreation = DatabaseCreation(this)
            val isLoginSuccessful = databaseCreation.retrieveData(email, password)

            if (isLoginSuccessful){
                val intent = Intent(
                    this@LoginActivity,
                    ProfileActivity::class.java
                )
                intent.putExtra("Key_email", email)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(applicationContext,
                    "email id and password didn't match",
                    Toast.LENGTH_LONG
                ).show()
            }
        }else{
            Toast.makeText(applicationContext,
                "Details not to be null",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}