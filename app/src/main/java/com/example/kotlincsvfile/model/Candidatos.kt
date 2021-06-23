package com.example.kotlincsvfile.model

import com.example.kotlincsvfile.filtro.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object Candidatos {

    val lista: ArrayList<Candidato> = ArrayList()
    val mapVaga: HashMap<String, Int> = HashMap()
    val mapEstado: HashMap<String, Int> = HashMap()

    fun getMapVaga() {
        lista.forEach() {
            val vaga: String = it.vaga
            val total = mapVaga.get(vaga)
            if (total != null) {
                mapVaga.put(vaga, total + 1)
            } else
                mapVaga[vaga] = 1
        }
    }

    fun getMapEstado() {
        lista.forEach() {
            val estado: String = it.estado
            val total = mapEstado.get(estado)
            if (total != null) {
                mapEstado.put(estado, total + 1)
            } else
                mapEstado[estado] = 1
        }
    }

    fun porcentagemVagas(): String {
        val quantidade = lista.size
        var porcentagem: Float
        var texto = "Proporção dos candidatos por vaga: \n"

        mapVaga.forEach() {
            porcentagem = ((it.value * 100).toFloat() / quantidade)
            texto += (it.key.uppercase() + "      %.2f".format(porcentagem) + "%\n")
        }
        return texto
    }

    fun idadeMedia(): String {
        val vaga = "QA".lowercase()
        var total = 0.0
        lista.forEach() {
            if (it.vaga.equals(vaga)) {
                total += it.idade
            }
        }
        val media: Double = total / mapVaga.get(vaga)!!
        return ("Idade média dos candidatos de " + vaga.uppercase() + ": " + "%.0f".format(media) + " anos \n")
    }

    fun ocorrenciaEstados(): String {
        return "Número de estados distintos presentes na lista: " + mapEstado.size + "\n"
    }

    fun menorOcorrenciaEstados(): String {
        val lista: ArrayList<Estado> = ArrayList()
        var estado: Estado

        mapEstado.forEach() {
            estado = Estado(it.key, it.value)
            lista.add(estado)
        }
        Collections.sort(lista)
        var texto: String = ("Rank dos 2 estados com menos ocorrência: \n")
        texto += ("#1 " + lista[0].uf + " - " + lista[0].quantidade + " candidatos \n")
        texto += ("#2 " + lista[1].uf + " - " + lista[1].quantidade + " candidatos \n")
        return texto
    }

    fun listaOrdenada(): String {
        Collections.sort(lista)
        return ("Gerando lista ordenada...")
    }

    fun procuraInstrutorIOs(lista: ArrayList<Candidato>): Candidato? {
        val filtroIdade = FiltroIdade(20, 31)
        val filtroIdadeImpar = FiltroIdadeImpar()
        val filtroPrimo = FiltroPrimo()
        val filtroSC = FiltroSC()
        val filtroPrimeiraLetraUltimoNome = FiltroPrimeiraLetraUltimoNome()

        var filtro: ArrayList<Candidato> = filtroIdade.filtrar(lista)

        filtro = filtroIdadeImpar.filtrar(filtro)
        filtro = filtroSC.filtrar(filtro)
        filtro = filtroPrimo.filtrar(filtro)
        filtro = filtroPrimeiraLetraUltimoNome.filtrar(filtro)
        return filtro.firstOrNull()
    }

    fun procuraInstrutorAndroid(lista: ArrayList<Candidato>, decada: Int, idadeIos: Int): Candidato? {
        val novoFiltroIdade = FiltroIdade(decada, idadeIos)
        val filtroVogais = FiltroVogais()
        val filtroUltimaLetraPrimeiroNome = FiltroUltimaLetraPrimeiroNome()
        val filtroIdadeImpar = FiltroIdadeImpar()
        val filtroSC = FiltroSC()

        var novoFiltro: ArrayList<Candidato> = novoFiltroIdade.filtrar(lista)
        novoFiltro = filtroUltimaLetraPrimeiroNome.filtrar(novoFiltro)
        novoFiltro = filtroVogais.filtrar(novoFiltro)
        novoFiltro = filtroIdadeImpar.filtrar(novoFiltro)
        novoFiltro = filtroSC.filtrar(novoFiltro)
        return novoFiltro.firstOrNull()
    }
}