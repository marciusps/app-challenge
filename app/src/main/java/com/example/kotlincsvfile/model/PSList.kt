package com.example.kotlincsvfile.model

interface PSList<T: Any> {
    fun contains(item: T): Boolean
    fun get(position: Int): T
    fun add(item: T)
    fun remove(position: Int): Boolean
    fun size(): Int
}