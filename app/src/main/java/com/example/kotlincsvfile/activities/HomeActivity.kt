package com.example.kotlincsvfile.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.kotlincsvfile.R

class HomeActivity : AppCompatActivity() {

    private lateinit var texto: TextView
    private lateinit var nome: TextView
    private var pressedTime: Long = 0

    override fun onBackPressed() {
        if(pressedTime + 3000 > System.currentTimeMillis()){
            super.onBackPressed()
            finish()
        }else
            Toast.makeText(baseContext, "Pressione 'Voltar' novamente para sair.", Toast.LENGTH_SHORT).show()
        pressedTime = System.currentTimeMillis()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btn_logout = findViewById(R.id.btn_logout) as View
        texto = findViewById(R.id.texto)
        nome = findViewById(R.id.nome)
        nome.text = intent.getStringExtra(EXTRA_CANDIDATO)

        btn_logout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                Toast.makeText(this@HomeActivity, "Logout efetuado!", Toast.LENGTH_SHORT).show()
            }
            startActivity(intent)
            finish()
        }
        texto.text = (intent.getStringExtra(EXTRA_MENSAGEM))
    }
}