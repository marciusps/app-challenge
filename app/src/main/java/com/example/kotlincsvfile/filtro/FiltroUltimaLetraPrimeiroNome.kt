package com.example.kotlincsvfile.filtro
import com.example.kotlincsvfile.model.Candidato
import com.example.kotlincsvfile.model.MyArrayList
import com.example.kotlincsvfile.model.PSList

class FiltroUltimaLetraPrimeiroNome :IFiltro {

    fun <T: Any> PSList<T>.forEach(action: (T) -> Unit){
        for (i in 0 until size()) action(this[i])
    }

    override fun filtrar(lista: MyArrayList<Candidato>): MyArrayList<Candidato> {
        val filtro :MyArrayList<Candidato> = MyArrayList()
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