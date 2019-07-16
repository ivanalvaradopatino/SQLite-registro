package com.ivan.sears.myapplicationsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
            //Se declaran nuestras variables globales para despues volver a ser utilizadas
                var no: String = ""
                var nom: String = ""
                var carr: String = ""
                var edad: String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun buscarEstudiante(v: View){
        if(etControl.text.isEmpty()){
            Toast.makeText(this, "Ingresa el numero de control para encontrar", Toast.LENGTH_LONG).show()
        }
        else
        {
            no = etControl.text.toString()
            val admin = adminBD(this)
            //PARA REALIZAR CONSULTA                  //0          1        2      3                             //variable
            val tupla = admin.Consulta("select noControl, nomEst, carrera, edad from Estudiante where noControl= '$no'") //Concatenación
            if (tupla!!.moveToFirst())//El doble signo de admiracion es que tiene que moverlo o mandarlo al falso
            {
                etNombre.setText(tupla.getString(1))
                etCarrera.setText(tupla.getString(2))
                etEdad.setText(tupla.getString(3))
                btnAgregar.isEnabled = false
                btnModificar.isEnabled = true
                btnLimpiar.isEnabled = true
            }
            else{
                Toast.makeText(this, "No existe el numero de control", Toast.LENGTH_LONG).show()
                etControl.requestFocus()
            }
        }
    }
    fun agregarEstudiante(v: View) {
        if (etControl.text.isEmpty() || etNombre.text.isEmpty() ||
            etCarrera.text.isEmpty() || etEdad.text.isEmpty()
        ) {
            Toast.makeText(this, "Falta información del estudiante", Toast.LENGTH_LONG).show()
            etControl.requestFocus()
        } else {
            leerCajas()
            //INSERCCION DE DATOS DENTRO DE LA BD
            val sentencia = "INSERT INTO Estudiante(noControl, nomEst, carrera, edad)"+"" +
                    " values('$no', '$nom', '$carr', $edad)"
            val admin = adminBD(this)
            if (admin.Ejecuta(sentencia) == 1)
            {
                Toast.makeText(this, "Estudiante SI AGREGADO", Toast.LENGTH_LONG).show()
                etControl.requestFocus()
        }
        else
        {
            Toast.makeText(this, "Estudiante NO AGREGADO", Toast.LENGTH_LONG).show()
            etControl.requestFocus()
        }
    }
    }
        fun actualizarEstudiante(v: View) {
            if (etControl.text.isEmpty() || etNombre.text.isEmpty() ||
                etCarrera.text.isEmpty() || etEdad.text.isEmpty()) {
                Toast.makeText(this, "Falta información del estudiante", Toast.LENGTH_LONG).show()
                etControl.requestFocus()
            } else {
                ModificarCajas()
                //ELIMINACION DE DATOS DENTRO DE LA BD
                val sentencia = "UPDATE Estudiante SET nomEst='$nom', carrera='$carr', edad=$edad WHERE noControl='$no'"
                val admin = adminBD(this)
                if (admin.Ejecuta(sentencia) == 1)
                {
                    Toast.makeText(this, "Estudiante MODIFICADO", Toast.LENGTH_LONG).show()
                    ModificarCajas()
                } else {
                    Toast.makeText(this, "Estudiante NO MODIFICADO", Toast.LENGTH_LONG).show()
                    etControl.requestFocus()
                }
            }
        }

        fun eliminarEstudiante(v: View) {
            if (etControl.text.isEmpty() || etNombre.text.isEmpty() ||
                etCarrera.text.isEmpty() || etEdad.text.isEmpty()
            ) {
                Toast.makeText(this, "Falta información del estudiante", Toast.LENGTH_LONG).show()
                etControl.requestFocus()
            } else {
                LimpiarCajas()
                //ELIMINACION DE DATOS DENTRO DE LA BD
                val sentencia = "DELETE FROM Estudiante WHERE noControl='$no'"
                val admin = adminBD(this)
                if (admin.Ejecuta(sentencia) == 1)
                {
                    Toast.makeText(this, "Estudiante ELIMINADO", Toast.LENGTH_LONG).show()
                    LimpiarCajas()
                } else
                {
                    Toast.makeText(this, "Estudiante NO ELIMINADO", Toast.LENGTH_LONG).show()
                    LimpiarCajas()
                }
            }
        }
        //Declaramos esta función para que lea las cajas de la aplicación
        fun  leerCajas(){
            no=""
            nom=""
            carr=""
            edad="0"
            no = etControl.text.toString()
            nom = etNombre.text.toString()
            carr = etCarrera.text.toString()
            edad = etEdad.text.toString()
        }
        fun  LimpiarCajas(){
            etControl.setText("")
            etNombre.setText("")
            etCarrera.setText("")
            etEdad.setText("")
            btnAgregar.isEnabled = false
            btnModificar.isEnabled = true
            btnLimpiar.isEnabled = true
            etControl.requestFocus()
    }
    fun  ModificarCajas() {
        no = etControl.text.toString()
        nom = etNombre.text.toString()
        carr = etCarrera.text.toString()
        edad = etEdad.text.toString()
    }
}