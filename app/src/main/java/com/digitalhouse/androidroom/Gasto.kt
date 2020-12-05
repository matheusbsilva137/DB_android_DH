package com.digitalhouse.androidroom

class Gasto (var id: Int, var nome: String, var valor: Double) {

    override fun toString(): String {
        return "Gasto(id=$id, nome='$nome', valor=$valor)"
    }
}