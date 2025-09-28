package com.example.listview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.app.Activity
import android.content.Intent
import android.widget.Button
import android.widget.EditText

class MainActivityDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_detail)

        val input = findViewById<EditText>(R.id.inputItem)
        val save = findViewById<Button>(R.id.btnSave)
        val back = findViewById<Button>(R.id.btnBack)

        back.setOnClickListener {
            finish()
        }

        save.setOnClickListener {
            val text = input.text.toString().trim()
            if (text.isNotEmpty()) {
                val data = Intent().putExtra("new_item", text)
                setResult(Activity.RESULT_OK, data)
                finish()
            } else {
                input.error = "Please enter something"
            }
        }
    }
}