package com.example.kotlincsvfile.model

class OcorrenciaEstados (var uf :String, var quantidade :Int) : Comparable<Any> {

    override fun toString(): String {
        return "OcorrenciaEstados($uf , $quantidade)"
    }

    override fun compareTo(other: Any): Int {
        val outro: String = (other as OcorrenciaEstados).uf
        val esse = uf
        return esse.compareTo(outro)
    }
}