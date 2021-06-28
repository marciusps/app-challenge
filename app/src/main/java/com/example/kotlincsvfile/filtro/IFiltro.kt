package com.example.kotlincsvfile.filtro

import com.example.kotlincsvfile.model.Candidato
import com.example.kotlincsvfile.model.MyArrayList

interface IFiltro {
    fun filtrar(lista : MyArrayList<Candidato>) :MyArrayList<Candidato>
}