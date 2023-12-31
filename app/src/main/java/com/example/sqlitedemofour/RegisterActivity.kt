package com.example.sqlitedemofour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    private var registrationName: EditText? = null
    private var registrationEmail: EditText? = null
    private var registrationNumber: EditText? = null
    private var registrationPassword: EditText? = null

    private lateinit var databaseCreation: DatabaseCreation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registrationName = findViewById(R.id.registrationName)
        registrationEmail = findViewById(R.id.registrationEmail)
        registrationNumber = findViewById(R.id.registrationNumber)
        registrationPassword = findViewById(R.id.registrationPassword)

        databaseCreation = DatabaseCreation(this)
    }
    fun registrationButton(view: View){
        val name = registrationName?.text.toString()
        val email = registrationEmail?.text.toString()
        val number = registrationNumber?.text.toString()
        val password = registrationPassword?.text.toString()

        if (name.isNotEmpty() && email.isNotEmpty() &&
            number.isNotEmpty() && password.isNotEmpty()
            ){
            val flag = databaseCreation.insertIntoTable(name, email, number, password)
            if (flag){ // True if Value added in database
                Toast.makeText(applicationContext,
                    "Registration Successfully",
                    Toast.LENGTH_LONG
                ).show()

                registrationName?.setText("")
                registrationEmail?.setText("")
                registrationNumber?.setText("")
                registrationPassword?.setText("")
            }else{
                Toast.makeText(applicationContext,
                    "Error.!!",
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