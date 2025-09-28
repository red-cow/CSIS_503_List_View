package com.example.listview

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import java.util.ArrayList

class MainActivity : ComponentActivity() {
    private lateinit var adapter: StringListAdapter
    private lateinit var countView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.myButton)
        val listView = findViewById<ListView>(R.id.listView)

        val items = resources.getStringArray(R.array.fruit_list).toCollection(ArrayList())
        countView = findViewById<TextView>(R.id.txtCount)

        adapter = StringListAdapter(items, layoutInflater){
            updateCount()
        }
        listView.adapter = adapter

        updateCount()


        listView.setOnItemClickListener { _, _, pos, _ ->
            val clicked = items[pos]
            Toast.makeText(this, "Clicked: $clicked", Toast.LENGTH_SHORT).show()
        }
        // Navigate to detail screen with data preloaded

        btn.setOnClickListener {
            try {
                val intent = Intent(this, MainActivityDetail::class.java).apply {
                putStringArrayListExtra("items", items) // pass data so detail loads immediately
                putExtra("title", "Fruit List (Detail)")
                }
                startActivity(intent)
            } catch (e: Exception) {
                // Show the error in a Toast so you can see it right away
                Toast.makeText(this, "Navigation failed: ${e.message}", Toast.LENGTH_LONG).show()
                // Also print full stack trace to Logcat
                e.printStackTrace()
            }
        }

    }
    private fun updateCount() {
        countView.text = "count: " + adapter.count.toString()
    }
}