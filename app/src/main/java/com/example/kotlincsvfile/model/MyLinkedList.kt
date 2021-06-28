package com.example.kotlincsvfile.model

import java.lang.IndexOutOfBoundsException

class Node<T : Any>(var data: T, var nextNode: Node<T>?)

class MyLinkedList<T : Any> : PSList<T> {

    private var count = 0
    private var first: Node<T>? = null


    override fun contains(item: T): Boolean {
        if (first == null)
            return false
        var aux = first
        while (aux?.nextNode != null) {
            if (aux.data == item)
                return true
            aux = aux.nextNode
        }
        return aux?.data == item
    }

    override operator fun get(position: Int): T {
        if (first == null && position !in 0 until count)
            throw IndexOutOfBoundsException("index error")
        var aux = first
        var contador = 0
        while (aux?.nextNode != null) {
            if (contador == position)
                return aux.data
            contador++
            aux = aux.nextNode
        }
        if (aux == null)
            throw IndexOutOfBoundsException("index error")

        return if (contador == position) aux.data else throw IndexOutOfBoundsException("index error")
    }

    override fun add(item: T) {
        if (count != 0) {
            val node: Node<T> = Node<T>(item, null)
            val aux: Node<T> = Node<T>(item, first)
            while (aux.nextNode?.nextNode != null) {
                aux.nextNode = aux.nextNode?.nextNode
            }
            aux.nextNode?.nextNode = node
        } else {
            first = Node(item, null)
        }
        count++
    }

    override fun remove(position: Int): Boolean {
        if (first == null && position !in 0 until count)
            return false
        var aux = first
        if (position == 0) {
            first = aux?.nextNode
            count--
            return true
        } else {
            var contador = 1
            while (aux?.nextNode != null) {
                if (contador != position) {
                    aux = aux.nextNode
                    contador++
                } else {
                    if (aux.nextNode?.nextNode == null)
                        aux.nextNode = null
                    else {
                        aux.nextNode = aux.nextNode?.nextNode
                        contador++
                        count--
                        return true
                    }
                }
            }
            count--
            return true
        }
    }

    override fun size(): Int {
        return count
    }

    override fun realSize(): Int {
        return count
    }
}