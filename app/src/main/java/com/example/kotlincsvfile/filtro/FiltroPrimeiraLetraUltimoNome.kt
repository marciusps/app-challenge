package com.example.kotlincsvfile.filtro
import com.example.kotlincsvfile.model.Candidato

class FiltroPrimeiraLetraUltimoNome :IFiltro{
    var letra :Char = '\u0000'

    override fun filtrar(lista: ArrayList<Candidato>): ArrayList<Candidato> {
        val filtro :ArrayList<Candidato> = ArrayList()
        var nome :ArrayList<String>
        lista.forEach(){
            nome = it.nome.split(" ") as ArrayList<String>
            val ultimoNome = nome[nome.size-1]
            val primeiraLetra = ultimoNome.get(0)
            if(primeiraLetra.equals('V')) {
                filtro.add(it)
            }
        }
        return filtro
    }
}