package com.example.es_tablewatcher

import android.app.Application
import com.example.es_tablewatcher.data.Repository

class App : Application() {
    companion object {
        lateinit var repository: Repository
    }

    override fun onCreate() {
        super.onCreate()
        repository = Repository(this)
        repository.checkFiles()
    }
}