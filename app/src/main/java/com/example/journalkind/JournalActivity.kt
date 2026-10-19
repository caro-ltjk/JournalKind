package com.example.journalkind

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class JournalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal)

        val btnVolver = findViewById<Button>(R.id.btnVolverHome)
        val btnEditar = findViewById<Button>(R.id.btnEditar)

        // Volver -> Regresa a la pantalla principal
        btnVolver.setOnClickListener {
            finish()
        }

        // Activa el modo de edición en la hoja blanca
        btnEditar.setOnClickListener {
            Toast.makeText(this, "Modo de edición activado. Aquí aparecerán las herramientas de stickers y texto.", Toast.LENGTH_SHORT).show()
        }
    }
}