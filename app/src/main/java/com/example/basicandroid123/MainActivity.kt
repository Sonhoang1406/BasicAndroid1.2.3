package com.example.basicandroid123

import android.app.Service
import android.content.ComponentName
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var boundService: BoundService? = null
    private var isServiceBound = false
    private val connection = object : ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
           val binder = p1 as BoundService.CalculateBinder
            boundService = binder.getService()
            isServiceBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            boundService = null
            isServiceBound = false
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if(isServiceBound){
            unbindService(connection)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCal : Button = findViewById(R.id.btnCal)
        val tvResults: TextView = findViewById(R.id.tvResults)
        val edtNumA: EditText = findViewById(R.id.edtNumA)
        val edtNumB: EditText = findViewById(R.id.edtNumB)


        btnCal.setOnClickListener {
             Cal()
        }
    }

    private fun Cal() {

    }
}