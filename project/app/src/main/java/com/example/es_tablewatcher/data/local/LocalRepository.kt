package com.example.es_tablewatcher.data.local

import android.content.Context
import com.example.es_tablewatcher.data.model.Mesa
import com.example.es_tablewatcher.data.model.Prato
import com.example.es_tablewatcher.data.model.Reserva
import com.example.es_tablewatcher.data.model.Utilizador
import com.example.es_tablewatcher.ui.MainActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.PrintWriter

class LocalRepository(private val context: Context) {

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
        val list : MutableList<Prato>? = gson.fromJson(json, listType)
        val ret = mutableListOf<Prato>()
        list?.let { ret.addAll(list) }
        ret.sortByDescending { it.id }
        ret.reverse()
        return ret
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
        val list : MutableList<Reserva>? = gson.fromJson(json, listType)
        val ret = mutableListOf<Reserva>()
        list?.let { ret.addAll(list) }
        ret.sortByDescending { it.id }
        ret.reverse()
        return ret
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
                if (mesa.id == it.mesaId && !it.terminated)
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
        val list : MutableList<Reserva>? =
            if (!json.isNullOrEmpty())
                gson.fromJson(json, listType)
            else
                mutableListOf()
        val ret = mutableListOf<Reserva>()
        list?.let { ret.addAll(list) }
        ret.add(reserva)
        ret.sortByDescending { it.id }
        ret.reverse()
        val listjson = gson.toJson(ret)
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
        val elementsToRemove = mutableListOf<Utilizador>()
        list.forEach {
            if(it.id == id) {
                elementsToRemove.add(it)
                return@forEach
            }
        }
        elementsToRemove.forEach { list.remove(it) }
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
        val elementsToRemove: MutableList<Prato> = mutableListOf()
        list.forEach {
            if(it.id == id) {
                elementsToRemove.add(it)
                return@forEach
            }
        }
        elementsToRemove.forEach { list.remove(it) }

        list.sortByDescending { it.id }
        list.reverse()
        val listjson = gson.toJson(list)
        writeToFile("Pratos", listjson)
        return true
    }

    fun adicionarPratos(reservaID: Int, pratos: MutableList<Prato>) : Boolean {
        val json = readFromFile("Reservas")
        val gson : Gson = Gson()
        val listType = object : TypeToken<List<Reserva>>() {}.type
        val list : MutableList<Reserva> =
            if (!json.isNullOrEmpty())
                gson.fromJson(json, listType)
            else
                mutableListOf()

        list.forEach { reserv ->
            if (reserv.id == reservaID) {
                reserv.listaPratos.addAll(pratos)
                reserv.listaPratos.sortByDescending { it.id }
                reserv.listaPratos.reverse()
                return@forEach
            }
        }

        val listjson = gson.toJson(list)
        writeToFile("Reservas", listjson)
        return true
    }

    fun fecharReserva(id: Int) : Double {
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
            if (reserv.id == id) {
                reserv.terminated = true
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

    fun calcularTotalReserva(id: Int) : Double {
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
            if (reserv.id == id) {

                reserv.listaPratos.forEach {
                    total += it.preco
                }
                reserv.total = total
                return@forEach
            }
        }
        return total
    }

    fun removerPratoAReserva(reservaID: Int, pratos: List<Prato>) : Boolean {
        val json = readFromFile("Reservas")
        val gson : Gson = Gson()
        val listType = object : TypeToken<List<Reserva>>() {}.type
        val list : MutableList<Reserva> =
            if (!json.isNullOrEmpty())
                gson.fromJson(json, listType)
            else
                mutableListOf()
        list.forEach { reserv ->
            if (reserv.id == reservaID) {
                reserv.listaPratos.forEach { pratoReserva->
                    pratos.forEach {
                        if (pratoReserva.id == it.id) {
                            reserv.listaPratos.remove(it)
                        }
                    }
                }
                reserv.listaPratos.sortByDescending { it.id }
                reserv.listaPratos.reverse()
            }
        }

        val listjson = gson.toJson(list)
        writeToFile("Reservas", listjson)
        return true
    }

    fun checkFiles() {
        val mesasFile: File = File(context.cacheDir, "Mesas.txt")
        val pratosFile: File = File(context.cacheDir, "Pratos.txt")
        val reservasFile: File = File(context.cacheDir, "Reservas.txt")
        val utilizadoresFile: File = File(context.cacheDir, "Utilizadores.txt")

        val defaultMesas = "[\n" +
                "  {\n" +
                "    \"id\":1,\n" +
                "    \"lugares\": 2\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\":2,\n" +
                "    \"lugares\": 4\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\":3,\n" +
                "    \"lugares\": 10\n" +
                "  }\n" +
                "]"
        val defaultPratos = ""
        val defaultReservas = ""
        val defaultUtilizadores = "[\n" +
                "   {\n" +
                "      \"id\":0,\n" +
                "      \"username\":\"admin\",\n" +
                "      \"password\":\"admin\",\n" +
                "      \"isAdmin\":true\n" +
                "   }\n" +
                "]"
        if (!mesasFile.exists()) {
            mesasFile.createNewFile()
            writeToFile("Mesas", defaultMesas)
        }
        if(!pratosFile.exists()) {
            pratosFile.createNewFile()
            writeToFile("Pratos", defaultPratos)
        }
        if(!reservasFile.exists()) {
            reservasFile.createNewFile()
            writeToFile("Reservas", defaultReservas)
        }
        if(!utilizadoresFile.exists()) {
            utilizadoresFile.createNewFile()
            writeToFile("Utilizadores", defaultUtilizadores)
        }
    }

    private fun readFromFile(fileName: String) : String {

        return File(context.cacheDir, "$fileName.txt").bufferedReader().use { it.readText() }
    }

    private fun writeToFile(fileName: String, data: String) {
        val pw = PrintWriter(File(context.cacheDir, "$fileName.txt"));
        pw.write(data)
        pw.close()
    }
}