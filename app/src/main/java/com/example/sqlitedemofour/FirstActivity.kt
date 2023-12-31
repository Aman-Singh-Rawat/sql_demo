package com.example.sqlitedemofour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

    }
    fun openLoginActivity(view: View){
        startActivity(
            Intent(
                this@FirstActivity,
                LoginActivity::class.java
            )
        )
        finish()
    }
    fun openRegisterActivity(view: View){
        startActivity(
            Intent(
                this@FirstActivity,
                RegisterActivity::class.java
            )
        )
        finish()
    }
}