package com.example.kotlincsvfile.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.kotlincsvfile.R
import com.example.kotlincsvfile.dao.CandidatoDao
import com.example.kotlincsvfile.model.*
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest
import java.util.*

const val EXTRA_MENSAGEM = "EXTRA_MENSAGEM"
const val EXTRA_CANDIDATO = "EXTRA_CANDIDATO"

class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    private val REQUEST_WRITE = 100
    var textao: String = ""
    private var pressedTime: Long = 0
    private lateinit var et_user_name: EditText
    private lateinit var et_password: EditText
    private lateinit var btn_login: View
    private lateinit var progressBar: View

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_user_name = findViewById(R.id.et_user_name) as EditText
        et_password = findViewById(R.id.et_password) as EditText
        btn_login = findViewById(R.id.btn_login) as View
        progressBar = findViewById(R.id.progressBar) as View
        callPermition()

        CandidatoDao.caminho = cacheDir.toString() + "/"
        CandidatoDao.separador = ";"
        CandidatoDao.saida = "Sorted_AppAcademy_Candidates.csv"
        val lista = CandidatoDao.lerArquivo(this)

        btn_login.setOnClickListener {
            val user_name = et_user_name.text.toString()
            val password = et_password.text.toString()
            if (user_name != "" || et_password.text.toString() != "") {
                if (permissaoUsuario(lista, user_name, password.toInt())) {
                    changeState(true)
                    delay {
                        val intent = Intent(this@MainActivity, HomeActivity::class.java).apply {
                            putExtra(EXTRA_MENSAGEM, textao)
                            putExtra(EXTRA_CANDIDATO, buscaUsuario(lista, user_name))
                            Toast.makeText(
                                this@MainActivity,
                                "Login efetuado com sucesso!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        startActivity(intent)
                        finish()
                    }
                } else Toast.makeText(
                    this@MainActivity,
                    "Login ou senha inválidos!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val candidatoIOs = Candidatos.procuraInstrutorIOs(lista)
        candidatoIOs?.let { println("Instrutor de IOs: " + it.nome) }

        val candidatoAndroid = Candidatos.procuraInstrutorAndroid(
            lista, (candidatoIOs?.idade?.div(10) ?: 0) * 10, candidatoIOs?.idade ?: 0
        )
        candidatoAndroid?.let { println("Instrutor de Android: " + it.nome) }

        estatisticaCandidato(lista, candidatoIOs, candidatoAndroid)

        testMyLinkedList()
        testMyArrayList()
    }

    inline fun <T: Any> PSList<T>.forEach(action: (T) -> Unit){
        for (i in 0 until size()) action(this[i])
    }

    private fun testMyArrayList() {
        val simpleArrayList = MyArrayList<String>()
        val (passed, message) = testPsListImplementation(simpleArrayList, false)
        Toast.makeText(
            this,
            if (passed) "All tests passed" else "Test failed with: ${message ?: ""}",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun testMyLinkedList() {
        val simpleLinkedList = MyLinkedList<String>()
        val (passed, message) = testPsListImplementation(simpleLinkedList, true)
        Toast.makeText(
            this,
            if (passed) "All tests passed" else "Test failed with: ${message ?: ""}",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun testPsListImplementation(
        psList: PSList<String>,
        isLinkedList: Boolean
    ): Pair<Boolean, String?> {
        try {
            psList.add("String 1")
            psList.add("String 2")
            psList.add("String 3")

            if (psList.size() != 3 || (!isLinkedList && psList.realSize() != DEFAULT_LIST_INIT_SIZE)) {
                return Pair(false, "Expected size = 3 and realSize = $DEFAULT_LIST_INIT_SIZE")
            }

            psList.add("String 4")
            psList.add("String 5")

            if (psList.size() != 5 || (!isLinkedList && psList.realSize() != DEFAULT_LIST_INIT_SIZE)) {
                return Pair(false, "Expected size = 5 and realSize = $DEFAULT_LIST_INIT_SIZE")
            }

            if (!psList.remove(4)) {
                return Pair(false, "Failed to remove element at position 4")
            }

            if (psList.size() != 4 || (!isLinkedList && psList.realSize() != DEFAULT_LIST_INIT_SIZE)) {
                return Pair(false, "Expected size = 4 and realSize = $DEFAULT_LIST_INIT_SIZE")
            }

            psList.add("String 5")

            if (!psList.contains("String 5")) {
                return Pair(false, "Expected to contain \"String 5\"")
            }

            psList.add("String 6")

            if (psList.size() != 6 || (!isLinkedList && psList.realSize() != DEFAULT_LIST_INIT_SIZE * 2)) {
                return Pair(false, "Expected size = 6 and realSize = $DEFAULT_LIST_INIT_SIZE")
            }

            for (i in 0 until psList.size()) {
                if (psList[i] != "String ${i + 1}") {
                    return Pair(false, "Expected string \"String ${i + 1}\" at position $i")
                }
            }

            psList.remove(1)

            if (psList[0] != "String 1") {
                return Pair(false, "Expected string \"String 1\" at position 0")
            }

            for (i in 1 until psList.size()) {
                if (psList[i] != "String ${i + 2}") {
                    return Pair(false, "Expected string \"String ${i + 2}\" at position $i")
                }
            }

            psList.remove(2)

            if (psList.size() != 4 || (!isLinkedList && psList.realSize() != DEFAULT_LIST_INIT_SIZE)) {
                return Pair(false, "Expected size = 4 and realSize = $DEFAULT_LIST_INIT_SIZE")
            }

            for (i in 2 until psList.size()) {
                if (psList[i] != "String ${i + 3}") {
                    return Pair(false, "Expected string \"String ${i + 3}\" at position $i")
                }
            }

            psList.remove(0)
            psList.remove(0)

            if (!psList.contains("String 5")) {
                return Pair(false, "Expected to contain \"String 5\"")
            }

            psList.remove(0)

            if (!psList.contains("String 6")) {
                return Pair(false, "Expected to contain \"String 6\"")
            }

            psList.remove(0)

            if (psList.size() != 0 || (!isLinkedList && psList.realSize() != DEFAULT_LIST_INIT_SIZE)) {
                return Pair(false, "Expected size = 0 and realSize = $DEFAULT_LIST_INIT_SIZE")
            }

            psList.add("final test")

            if (psList.size() != 1 || (!isLinkedList && psList.realSize() != DEFAULT_LIST_INIT_SIZE)) {
                return Pair(false, "Expected size = 1 and realSize = $DEFAULT_LIST_INIT_SIZE")
            }

            if (psList[0] != "final test") {
                return Pair(false, "Expected string \"final test\" at position 0")
            }

            return Pair(true, null)
        } catch (e: Exception) {
            return Pair(false, e.message ?: "Unknown exception")
        }
    }

    fun changeState(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
            btn_login.apply {
                isClickable = false
                isFocusable = false
                alpha = 0.5f
            }
        } else {
            progressBar.visibility = View.INVISIBLE
            btn_login.apply {
                isClickable = true
                isFocusable = true
                alpha = 1f
            }
        }
    }

    override fun onBackPressed() {
        if (pressedTime + 3000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        }
        pressedTime = System.currentTimeMillis()
    }

    private fun delay(delay: Long = 1500, action: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(action, delay)
    }

    fun buscaUsuario(lista: MyArrayList<Candidato>, nome: String): String? {
        val data: List<String> = nome.split("_")
        val nomeFormatado = data[0].replaceFirstChar {
            data[0].first().uppercase()
        } + " " + data[1].replaceFirstChar { data[1].first().uppercase() }
        lista.forEach() {
            if (nomeFormatado == it.nome) {
                return "${it.nome}, ${it.vaga.uppercase()}, ${it.idade} anos, ${it.estado}"
            }
        }
        return null
    }

    fun permissaoUsuario(lista: MyArrayList<Candidato>, nome: String, idade: Int): Boolean {
        lista.forEach() {
            val nomeFormatado = it.nome.lowercase().replace(" ", "_")
            if (nome == nomeFormatado && (Calendar.getInstance()
                    .get(Calendar.YEAR) - it.idade == idade)
            )
                return true
        }
        return false
    }

    fun estatisticaCandidato(
        lista: MyArrayList<Candidato>,
        candidatoIOs: Candidato?,
        candidatoAndroid: Candidato?
    ) {
        montaTexto(Candidatos.porcentagemVagas())
        montaTexto(Candidatos.idadeMedia())
        montaTexto(Candidatos.ocorrenciaEstados())
        montaTexto(Candidatos.menorOcorrenciaEstados())
        montaTexto(Candidatos.listaOrdenada())
        montaTexto(CandidatoDao.salvarArquivo(lista))
        montaTexto("Instrutor de IOs: " + (candidatoIOs?.nome ?: "inexistente"))
        montaTexto("Instrutor de Android: " + (candidatoAndroid?.nome ?: " Inexistente"))
    }

    private fun montaTexto(texto: String) {
        textao = textao + "\n" + texto
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {}

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {}

    private fun callPermition() {
        EasyPermissions.requestPermissions(
            PermissionRequest.Builder(
                this, REQUEST_WRITE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).setRationale("A permissão é necessária para que o aplicativo funcione.")
                .setPositiveButtonText("Ok")
                .setNegativeButtonText("Cancelar")
                .build()
        )
    }
}