package com.example.kotlincsvfile.filtro
import com.example.kotlincsvfile.model.Candidato

class FiltroIdade(var min: Int, var max: Int) :IFiltro {

    override fun filtrar(lista :ArrayList<Candidato>) :ArrayList<Candidato>{
        val filtro :ArrayList<Candidato> = ArrayList()

        lista.forEach(){
            if(it.idade>= min && it.idade<max)
                filtro.add(it)
        }
        return filtro
    }
}