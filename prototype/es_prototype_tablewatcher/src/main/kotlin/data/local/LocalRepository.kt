package data.local

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import data.model.Mesa
import data.model.Prato
import data.model.Reserva
import data.model.Utilizador
import java.io.File
import java.io.PrintWriter

class LocalRepository {

    fun login (username: String, password: String): Utilizador? {
        val userList = obterUtilizadores()
        userList.forEach {
            if (it.username == username && it.password == password) {
                return it
            }
        }
        return null
    }
    fun obterPratos() : List<Prato> {
        val json = readFromFile("Pratos")
        val gson : Gson = Gson()
        val listType = object : TypeToken<List<Prato>>() {}.type
        val list : MutableList<Prato> = gson.fromJson(json, listType)
        list.sortByDescending { it.id }
        list.reverse()
        return list
    }

    fun obterUtilizadores() : List<Utilizador> {
        val json = readFromFile("Utilizadores")
        val gson : Gson = Gson()
        val listType = object : TypeToken<List<Utilizador>>() {}.type
        val list : MutableList<Utilizador> = gson.fromJson(json, listType)
        list.sortByDescending { it.id }
        list.reverse()
        return list
    }

    fun obterReservas() : List<Reserva> {
        val json = readFromFile("Reservas")
        val gson : Gson = Gson()
        val listType = object : TypeToken<List<Reserva>>() {}.type
        val list : MutableList<Reserva> = gson.fromJson(json, listType)
        list.sortByDescending { it.id }
        list.reverse()
        return list
    }

    fun obterMesas() : List<Mesa> {
        val json = readFromFile("Mesas")
        val gson : Gson = Gson()
        val listType = object : TypeToken<List<Mesa>>() {}.type
        val listMesas : MutableList<Mesa> = gson.fromJson(json, listType)

        val listReservas = obterReservas()

        val listMesasDisponiveis = mutableListOf<Mesa>()

        listMesas.forEach { mesa ->
            var isOccupied = false
            listReservas.forEach {
                if (mesa.id == it.mesaId)
                    isOccupied = true
            }
            if (!isOccupied) {
                listMesasDisponiveis.add(mesa)
            }
        }


        listMesasDisponiveis.sortByDescending { it.id }
        listMesasDisponiveis.reverse()
        return listMesasDisponiveis
    }

    fun adicionarPrato(prato: Prato): Boolean {
        val json = readFromFile("Pratos")
        val gson : Gson = Gson()
        val listType = object : TypeToken<List<Prato>>() {}.type
        val list : MutableList<Prato> =
            if (!json.isNullOrEmpty())
                gson.fromJson(json, listType)
            else
                mutableListOf()
        list.add(prato)
        list.sortByDescending { it.id }
        list.reverse()
        val listjson = gson.toJson(list)
        writeToFile("Pratos", listjson)
        return true
    }

    fun adicionarUtilizador(utilizador: Utilizador): Boolean{
        val json = readFromFile("Utilizadores")
        val gson : Gson = Gson()
        val listType = object : TypeToken<List<Utilizador>>() {}.type
        val list : MutableList<Utilizador> =
            if (!json.isNullOrEmpty())
                gson.fromJson(json, listType)
            else
                mutableListOf()
        list.add(utilizador)
        list.sortByDescending { it.id }
        list.reverse()
        val listjson = gson.toJson(list)
        writeToFile("Utilizadores", listjson)
        return true
    }

    fun adicionarReserva(reserva : Reserva) : Boolean {
        val json = readFromFile("Reservas")
        val gson : Gson = Gson()
        val listType = object : TypeToken<List<Reserva>>() {}.type
        val list : MutableList<Reserva> =
            if (!json.isNullOrEmpty())
                gson.fromJson(json, listType)
            else
                mutableListOf()
        list.add(reserva)
        list.sortByDescending { it.id }
        list.reverse()
        val listjson = gson.toJson(list)
        writeToFile("Reservas", listjson)
        return true
    }

    fun removerUtilizador(id: Int) : Boolean {
        val json = readFromFile("Utilizadores")
        val gson : Gson = Gson()
        val listType = object : TypeToken<List<Utilizador>>() {}.type
        val list : MutableList<Utilizador> =
            if (!json.isNullOrEmpty())
                gson.fromJson(json, listType)
            else
                mutableListOf()
        list.forEach {
            if(it.id == id) {
                list.remove(it)
                return@forEach
            }
        }
        list.sortByDescending { it.id }
        list.reverse()
        val listjson = gson.toJson(list)
        writeToFile("Utilizadores", listjson)
        return true
    }

    fun removerPrato(id: Int) : Boolean {
        val json = readFromFile("Pratos")
        val gson : Gson = Gson()
        val listType = object : TypeToken<List<Prato>>() {}.type
        val list : MutableList<Prato> =
            if (!json.isNullOrEmpty())
                gson.fromJson(json, listType)
            else
                mutableListOf()
        list.forEach {
            if(it.id == id) {
                list.remove(it)
                return@forEach
            }
        }
        list.sortByDescending { it.id }
        list.reverse()
        val listjson = gson.toJson(list)
        writeToFile("Pratos", listjson)
        return true
    }

    fun adicionarPratos(reserva: Reserva, pratos: MutableList<Prato>) : Boolean {
        val json = readFromFile("Reservas")
        val gson : Gson = Gson()
        val listType = object : TypeToken<List<Reserva>>() {}.type
        val list : MutableList<Reserva> =
            if (!json.isNullOrEmpty())
                gson.fromJson(json, listType)
            else
                mutableListOf()

        list.forEach { reserv ->
            if (reserv.id == reserva.id) {
                reserv.listaPratos.sortByDescending { it.id }
                reserv.listaPratos.reverse()
                pratos.sortByDescending { it.id }
                pratos.reverse()
                reserv.listaPratos.addAll(pratos)
                return@forEach
            }
        }

        val listjson = gson.toJson(list)
        writeToFile("Reservas", listjson)
        return true
    }

    fun fecharReserva(reserva: Reserva) : Double {
        val json = readFromFile("Reservas")
        val gson : Gson = Gson()
        val listType = object : TypeToken<List<Reserva>>() {}.type
        val list : MutableList<Reserva> =
            if (!json.isNullOrEmpty())
                gson.fromJson(json, listType)
            else
                mutableListOf()
        var total : Double = 0.0
        list.forEach { reserv ->
            if (reserv.id == reserva.id) {

                reserv.listaPratos.forEach {
                    total += it.preco
                }
                reserv.total = total
                return@forEach
            }
        }

        val listjson = gson.toJson(list)
        writeToFile("Reservas", listjson)
        return total
    }

    fun removerPratoAReserva(reserva: Reserva, prato: Prato) : Boolean {
        val json = readFromFile("Reservas")
        val gson : Gson = Gson()
        val listType = object : TypeToken<List<Reserva>>() {}.type
        val list : MutableList<Reserva> =
            if (!json.isNullOrEmpty())
                gson.fromJson(json, listType)
            else
                mutableListOf()
        list.forEach { reserv ->
            if (reserv.id == reserva.id) {
                reserv.listaPratos.forEach {
                    if (it.id == prato.id) {
                        reserv.listaPratos.remove(it)
                    }
                }
                return@forEach
            }
        }

        val listjson = gson.toJson(list)
        writeToFile("Reservas", listjson)
        return true
    }

    private fun readFromFile(fileName: String) : String {
        return File("src/main/kotlin/data/local/dbs/$fileName.txt").bufferedReader().use { it.readText() }
    }

    private fun writeToFile(fileName: String, data: String) {
        val pw = PrintWriter("src/main/kotlin/data/local/dbs/$fileName.txt")
        pw.write(data)
        pw.close()
    }
}