package com.digitalhouse.androidroom

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHandler(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private val DATABASE_NAME = "app_data_base"
        private val DATABASE_VERSION = 1

        //constante tabela gasto
        private val KEY_ID = "id"
        private val KEY_NOME = "nome"
        private val KEY_VALOR = "valor"
        private val TABLE_GASTO = "gastos"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_GASTO = "CREATE TABLE $TABLE_GASTO("+
                "$KEY_ID INTEGER PRIMARY KEY,"+
                "$KEY_NOME TEXT,"+
                "$KEY_VALOR REAL)"
        db?.execSQL(CREATE_TABLE_GASTO)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS "+ TABLE_GASTO)
    }

    //INSERT INTO gastos(id, nome, valor) VALUES (1, "comida, 10.0)
    //insert
    fun addGastoDatabase(gasto: Gasto): Long{
        val db = this.writableDatabase

        val content = ContentValues()
        content.put(KEY_NOME, gasto.nome)
        content.put(KEY_VALOR, gasto.valor)

        val res: Long = db.insert(TABLE_GASTO, null, content)
        db.close()
        return res
    }

    //select
    fun getAllGastos(): List<Gasto>{
        var listGasto = ArrayList<Gasto>()
        val query = "SELECT * FROM $TABLE_GASTO"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try{
            cursor = db.rawQuery(query, null)
            if (cursor.moveToFirst()){
                do{
                    var id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                    var nome = cursor.getString(cursor.getColumnIndex(KEY_NOME))
                    var valor = cursor.getDouble(cursor.getColumnIndex(KEY_VALOR))
                    listGasto.add(Gasto(id, nome, valor))
                }while (cursor.moveToNext())
            }
        }catch (e: SQLException){
            Log.e("DatabaseHandler", e.toString())
        }

        return listGasto
    }
}