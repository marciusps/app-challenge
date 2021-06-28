package com.example.kotlincsvfile.filtro
import com.example.kotlincsvfile.model.Candidato
import com.example.kotlincsvfile.model.MyArrayList
import com.example.kotlincsvfile.model.PSList
import kotlin.collections.ArrayList

class FiltroVogais :IFiltro {

    fun <T: Any> PSList<T>.forEach(action: (T) -> Unit){
        for (i in 0 until size()) action(this[i])
    }

    override fun filtrar(lista: MyArrayList<Candidato>): MyArrayList<Candidato> {
        val filtro :MyArrayList<Candidato> = MyArrayList()
        var nome :ArrayList<String>
        lista.forEach(){
            nome = it.nome.split(" ") as ArrayList<String>
            val primeiroNome = nome[0].lowercase()
            if(vogais(primeiroNome))
                filtro.add(it)
        }
        return filtro
    }

    fun vogais(nome :String) :Boolean{
        var cont :Int = 0
        var i :Int = 0
        var j :Int = 0
        val vogais :String = "AEIOUaeiouÁÉÍÓÚáéíóúÂÊÎÔÛâêîôûÃÕãõÀÈÌÒÙàèìòù"

        for (i in 0..vogais.length-1)
            for (j in 0..nome.length-1){
                val letra :Char = nome[j]
                if(vogais[i].equals(letra))
                    cont++
            }
        return cont ==3
    }
}