package com.example.kotlincsvfile.filtro
import com.example.kotlincsvfile.model.Candidato

class FiltroPrimo :IFiltro {

    override fun filtrar(lista :ArrayList<Candidato>) :ArrayList<Candidato>{
        val filtro :ArrayList<Candidato> = ArrayList()
        lista.forEach(){
            if (ePrimo(it.idade))
                filtro.add(it)
        }
        return filtro
    }

    fun ePrimo(idade :Int) :Boolean{
        var i :Int= 2
        while (i<idade){
            if(idade%i == 0)
                return false
            i++
        }
        return true
    }
}