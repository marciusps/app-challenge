package com.example.kotlincsvfile.filtro
import com.example.kotlincsvfile.model.Candidato

class FiltroIdadeImpar :IFiltro {

    override fun filtrar(lista :ArrayList<Candidato>) :ArrayList<Candidato>{
        val filtro :ArrayList<Candidato> = ArrayList()
        lista.forEach(){
            if (it.idade%2!=0)
                filtro.add(it)
        }
        return filtro
    }
}