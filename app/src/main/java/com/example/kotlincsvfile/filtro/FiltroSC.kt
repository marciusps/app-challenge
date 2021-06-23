package com.example.kotlincsvfile.filtro
import com.example.kotlincsvfile.model.Candidato

class FiltroSC :IFiltro {

    override fun filtrar(lista :ArrayList<Candidato>) :ArrayList<Candidato>{
        val filtro :ArrayList<Candidato> = ArrayList()
        lista.forEach(){
            if(it.estado.equals("SC"))
                filtro.add(it)
        }
        return filtro
    }
}