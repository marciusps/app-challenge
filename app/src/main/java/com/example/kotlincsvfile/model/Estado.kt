package com.example.kotlincsvfile.model

class Estado (val uf :String, val quantidade :Int) : Comparable<Any> {

    override operator fun compareTo(other: Any): Int {
        val outro: Int = (other as Estado).quantidade
        val esse = quantidade
        return esse.compareTo(outro)
    }


}