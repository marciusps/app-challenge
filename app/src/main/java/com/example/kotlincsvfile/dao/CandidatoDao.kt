package com.example.kotlincsvfile.dao

import android.content.Context
import com.example.kotlincsvfile.model.Candidato
import com.example.kotlincsvfile.model.Candidatos
import java.io.*

object CandidatoDao {
    lateinit var caminho: String
    lateinit var separador: String
    lateinit var saida: String

    fun lerArquivo(context: Context): ArrayList<Candidato> {
        return Candidatos.lista.also {
            val streamReader = InputStreamReader(context.assets.open("AppAcademy_Candidates.csv"))
            val bufferedReader = BufferedReader(streamReader)
            var row: List<String>
            bufferedReader.readLine()

            while (bufferedReader.ready()) {
                row = bufferedReader.readLine().split(";")
                Candidatos.lista.add(
                    Candidato(
                        row[0],
                        row[1],
                        row[2].filter { it.isDigit() }.toInt(),
                        row[3]
                    )
                )
            }
            Candidatos.getMapVaga()
            Candidatos.getMapEstado()
        }
    }

    fun salvarArquivo(lista: ArrayList<Candidato>): String {
        File(caminho + saida).bufferedWriter().use { out ->
            out.write("Nome;Vaga;Idade;Estado\n")
            lista.forEach() {
                out.write(it.nome + separador + it.vaga + separador + it.idade + separador + it.estado + "\n")
            }
        }
        return "Lista ordenada salva como: " + saida + "\n"
    }
}