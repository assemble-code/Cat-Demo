package com.demo.cat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.demo.cat.R
import com.demo.cat.util.Helper

class LoginActivity : AppCompatActivity() {
    private lateinit var btnSignUp: TextView
    private lateinit var btnLogin: Button
    lateinit var etUserName: EditText
    lateinit var etPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnSignUp = findViewById(R.id.btnSignUp)
        btnLogin = findViewById(R.id.btnLogin)

        etUserName = findViewById(R.id.etUserName)
        etPassword = findViewById(R.id.etPassword)


        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {

            //validate the user info

            if (Helper.verifyCred(this, etUserName.text.toString(), etPassword.text.toString())) {


                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid Username Or Password", Toast.LENGTH_SHORT).show()
            }

        }


    }
}