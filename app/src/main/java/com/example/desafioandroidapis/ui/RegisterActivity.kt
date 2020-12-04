package com.example.desafioandroidapis.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.example.desafioandroidapis.R

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val toolbar = findViewById<Toolbar>(R.id.tbApp)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        toolbar.title = resources.getString(R.string.register)

        findViewById<Button>(R.id.btnRegister).setOnClickListener {
            startActivity(Intent(this, ComicDisplayActivity::class.java))
        }
    }
}