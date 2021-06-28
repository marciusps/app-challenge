package com.example.kotlincsvfile.filtro
import com.example.kotlincsvfile.model.Candidato
import com.example.kotlincsvfile.model.MyArrayList
import com.example.kotlincsvfile.model.PSList

class FiltroPrimeiraLetraUltimoNome :IFiltro{
    var letra :Char = '\u0000'

    fun <T: Any> PSList<T>.forEach(action: (T) -> Unit){
        for (i in 0 until size()) action(this[i])
    }

    override fun filtrar(lista: MyArrayList<Candidato>): MyArrayList<Candidato> {
        val filtro : MyArrayList<Candidato> = MyArrayList()
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