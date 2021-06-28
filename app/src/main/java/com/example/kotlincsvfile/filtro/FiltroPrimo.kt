package com.example.kotlincsvfile.filtro
import com.example.kotlincsvfile.model.Candidato
import com.example.kotlincsvfile.model.MyArrayList
import com.example.kotlincsvfile.model.PSList

class FiltroPrimo :IFiltro {

    fun <T: Any> PSList<T>.forEach(action: (T) -> Unit){
        for (i in 0 until size()) action(this[i])
    }

    override fun filtrar(lista :MyArrayList<Candidato>) :MyArrayList<Candidato>{
        val filtro : MyArrayList<Candidato> = MyArrayList()
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