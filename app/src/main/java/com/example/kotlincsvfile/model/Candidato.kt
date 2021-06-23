package com.example.kotlincsvfile.model

import java.io.Serializable
import java.text.Collator
import java.util.*

class Candidato : Comparable<Any> , Serializable {
    val nome: String
    var vaga :String
        get() {
            return field
        }
        set(value) {field = value.lowercase()}
    val idade: Int
    val estado: String

    constructor(nome: String, vaga: String, idade: Int, estado: String) {
        this.nome = nome
        this.vaga = vaga
        this.idade = idade
        this.estado = estado
    }

    constructor(nome: String, idade: Int, estado: String) {
        this.nome = nome
        this.vaga = "QA"
        this.idade = Calendar.getInstance().get(Calendar.YEAR) - idade
        this.estado = estado
    }

    override fun toString(): String {
        return "Candidato(nome='$nome', vaga='$vaga', idade=$idade, uf='$estado')"
    }

    override operator fun compareTo(other: Any): Int {
        other as Candidato
        val collator: Collator = Collator.getInstance(Locale("pt", "BR"))
        collator.setStrength(Collator.PRIMARY)
        return collator.compare(this.nome, other.nome)
    }
}