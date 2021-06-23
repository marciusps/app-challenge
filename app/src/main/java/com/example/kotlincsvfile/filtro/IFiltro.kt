package com.example.kotlincsvfile.filtro

import com.example.kotlincsvfile.model.Candidato

interface IFiltro {
    fun filtrar(lista :ArrayList<Candidato>) :ArrayList<Candidato>
}