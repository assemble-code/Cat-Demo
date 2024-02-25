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

class SignUp : AppCompatActivity() {
    lateinit var singIn: TextView
    lateinit var createAccount: Button
    lateinit var etUserName: EditText
    lateinit var etPassword: EditText
    lateinit var etPasswordConfirm: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        singIn = findViewById(R.id.btnLogin)
        createAccount = findViewById(R.id.btnCreateAccount)

        etUserName = findViewById(R.id.etUserName)
        etPassword = findViewById(R.id.etPassword)
        etPasswordConfirm = findViewById(R.id.etConfirmPassword)

        singIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }

        createAccount.setOnClickListener {


            if (validateInput()) {
                Helper.saveUserData(this, etUserName.text.toString(), etPassword.text.toString())
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
            }


        }


    }

    private fun validateInput(): Boolean {
        if (etUserName.text.isBlank() && etUserName.text.isEmpty()) {
            return false
        }

        if (etPassword.text.isBlank() && etPassword.text.isEmpty()) {
            return false
        }

        if (etPasswordConfirm.text.isBlank() && etPasswordConfirm.text.isEmpty()) {
            return false
        }

        if (!etPassword.text.toString().equals(etPasswordConfirm.text.toString())) return false

        return true
    }
}