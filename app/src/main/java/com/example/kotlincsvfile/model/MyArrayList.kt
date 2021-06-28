package com.example.kotlincsvfile.model

import java.lang.IndexOutOfBoundsException

val DEFAULT_LIST_INIT_SIZE = 5

class MyArrayList<T : Any> : PSList<T> {

    private var dataArray: Array<Any?> = Array(DEFAULT_LIST_INIT_SIZE) {
        Any()
    }

    private var count = 0

    override fun contains(item: T): Boolean {
        dataArray.forEach {
            if (it == item) {
                return true
            }
        }
        return false
    }

    override operator fun get(position: Int): T {
        if ((position > count - 1) && position < 0) {
            throw IndexOutOfBoundsException("index error")
        }
        return dataArray[position] as T
    }

    override fun add(item: T) {
        if (count < dataArray.size) {
            dataArray[count++] = item
            return
        }
        val newArray: Array<Any?> = Array(dataArray.size + DEFAULT_LIST_INIT_SIZE) {
            if (it < count)
                dataArray[it]
            else
                null
        }
        newArray[count++] = item
        dataArray = newArray
    }

    override fun remove(position: Int): Boolean {
        if (count == 0 && position < 0 && position > count)
            return false

        for(i in position..count-1){
            if (i==count-1)
                dataArray[i]=null
            else{
                dataArray[i]=dataArray[i+1]
            }
        }
        count--

        val tamanho = dataArray.size - DEFAULT_LIST_INIT_SIZE
        if(tamanho >= DEFAULT_LIST_INIT_SIZE){
            val newArray: Array<Any?> = Array(dataArray.size - DEFAULT_LIST_INIT_SIZE) {
                if (it < count)
                    dataArray[it]
                else
                    null
            }
            dataArray = newArray
        }
        return true
    }

    override fun size(): Int {
        return count
    }

    override fun realSize(): Int {
        return dataArray.size
    }
}