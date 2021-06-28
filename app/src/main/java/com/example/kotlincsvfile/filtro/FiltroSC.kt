package com.example.kotlincsvfile.filtro
import com.example.kotlincsvfile.model.Candidato
import com.example.kotlincsvfile.model.MyArrayList
import com.example.kotlincsvfile.model.PSList

class FiltroSC :IFiltro {

    fun <T: Any> PSList<T>.forEach(action: (T) -> Unit){
        for (i in 0 until size()) action(this[i])
    }

    override fun filtrar(lista :MyArrayList<Candidato>) :MyArrayList<Candidato>{
        val filtro : MyArrayList<Candidato> = MyArrayList()
        lista.forEach(){
            if(it.estado.equals("SC"))
                filtro.add(it)
        }
        return filtro
    }
}