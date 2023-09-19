package com.example.basicandroid123

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var edtNumA: EditText
    private lateinit var edtNumB: EditText
    private lateinit var tvResult: TextView
    private lateinit var btnCal: Button
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

    override fun onStart() {
        super.onStart()
        if(!isServiceBound){
            val intent: Intent = Intent(this,BoundService::class.java)
            bindService(intent, connection,Context.BIND_AUTO_CREATE)
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
        edtNumA = findViewById(R.id.edtNumA)
        edtNumB = findViewById(R.id.edtNumB)
        tvResult = findViewById(R.id.tvResults)
        btnCal = findViewById(R.id.btnCal)
        btnCal.setOnClickListener {
            sum(btnCal)
        }
    }

    fun sum(view: View){
        val numA = edtNumA.text.toString().toInt()
        val numB = edtNumB.text.toString().toInt()
        tvResult.text = ("" + boundService?.performCalculation(numA, numB))
    }


}