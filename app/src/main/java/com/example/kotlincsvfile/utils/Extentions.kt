package com.example.kotlincsvfile.utils

import com.example.kotlincsvfile.model.PSList

inline fun <T: Any> PSList<T>.forEach(action: (T) -> Unit){
    for (i in 0 until size()) action(this[i])
}