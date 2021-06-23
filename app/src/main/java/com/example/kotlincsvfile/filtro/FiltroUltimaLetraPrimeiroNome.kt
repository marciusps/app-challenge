package com.example.kotlincsvfile.filtro
import com.example.kotlincsvfile.model.Candidato

class FiltroUltimaLetraPrimeiroNome :IFiltro {

    override fun filtrar(lista: ArrayList<Candidato>): ArrayList<Candidato> {
        val filtro :ArrayList<Candidato> = ArrayList()
        var nome :ArrayList<String>
        lista.forEach(){

            nome = it.nome.split(" ") as ArrayList<String>
            val primeiroNome = nome[0]
            val ultimaLetra = primeiroNome[primeiroNome.length-1]

            if(ultimaLetra.equals('o')) {
                filtro.add(it)
            }
        }
        return filtro
    }
}