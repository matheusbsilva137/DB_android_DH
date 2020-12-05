package com.digitalhouse.androidroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val databaseHandler: DatabaseHandler =  DatabaseHandler(this)

        val resIns = databaseHandler.addGastoDatabase(Gasto(1, "Comida", 10.0))
        Log.i("MainActivity", resIns.toString())

        val res = databaseHandler.getAllGastos()
        res.forEach {
            Log.i("MainActivity", it.toString())
        }
    }
}