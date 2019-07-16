package com.ivan.sears.myapplicationsqlite

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class adminBD (context: Context): SQLiteOpenHelper(context, DATABASE,null,1)
{
    companion object{
        val DATABASE = "Escuela"//nombre de la base de datos
    }
    override fun onCreate(db: SQLiteDatabase?) {
        //el oncreate se ejecuta por primera vez cuando se instala la APP
        db?.execSQL(
            "create table Estudiante(" +
                    "noControl text primary key, " +
                    "nomEst text, " +
                    "carrera text, " +
                    "edad integer)"
        )
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //ejecuta para enviar el eliminar el actualiza, etc
    fun Ejecuta(sentencia: String):Int
    {
        try {
            //abrimos la base de datos en modo de lectuta y escritura
            val db = this.writableDatabase
            db.execSQL(sentencia)
            db.close()
            return 1

        }
        catch (ex:Exception)
        {
            return 0

        }

    }
    //funcion de consulta
    fun  Consulta(select: String): Cursor?// signo de ? porque puede venir vacia o no existe el est por ejemplo
    {
        try
        {
            //no abrimos base de escritura porque vamos a consultar informacion de la base de datos
            val db=this.readableDatabase
            return db.rawQuery(select,null)
        }
        catch (ex:Exception)
        {
            return null;
        }

    }
    //
}