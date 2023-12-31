package com.example.sqlitedemofour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {

    private var profileName: TextView? = null
    private var profileEmail: TextView? = null
    private var profileNumber: TextView? = null

    private lateinit var databaseCreation: DatabaseCreation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profileName = findViewById(R.id.profileName)
        profileEmail = findViewById(R.id.profileEmail)
        profileNumber = findViewById(R.id.profileNumber)

        getUserDetails()

    }
    private fun getUserDetails() {
        Log.d("Message", "Yes, it's work")

        val intent = intent
        val email: String? = intent.getStringExtra("key_email")

        if (email != null) {
            val arrayList: ArrayList<ContainerClass> = databaseCreation.getLoggedUserDetails(email)

            if (arrayList.isNotEmpty()) {
                profileName?.text = arrayList[0].name
                profileEmail?.text = arrayList[0].email
                profileNumber?.text = arrayList[0].number

                Log.d("profileName", arrayList[0].name)
                Log.d("profileEmail", arrayList[0].email)
                Log.d("profileNumber", arrayList[0].number)
            } else {
                Log.e("getUserDetails", "User details list is empty")
            }
        } else {
            Log.e("getUserDetails", "Email is null")
        }
    }

    fun profileLogoutButton(view: View){
        Log.d("Message","Yes its button")
        startActivity(
            Intent(
                this@ProfileActivity,
                LoginActivity::class.java
            )
        )
        finish()
    }
}