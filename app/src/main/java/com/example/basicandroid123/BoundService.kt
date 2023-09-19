package com.example.basicandroid123

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class BoundService: Service( ) {
    private val binder = CalculateBinder()
    inner class CalculateBinder: Binder() {
        fun getService(): BoundService{
            return this@BoundService
        }
    }
    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    fun performCalculation(numA: Int, numB: Int): Int{
        return numA + numB
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
            super.onDestroy()
    }
}