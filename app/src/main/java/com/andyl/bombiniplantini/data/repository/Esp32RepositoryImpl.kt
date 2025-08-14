package com.andyl.bombiniplantini.data.repository

import com.andyl.bombiniplantini.core.di.Urls.BASE_URL
import com.andyl.bombiniplantini.domain.repository.Esp32Repository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class Esp32RepositoryImpl(private val httpClient: HttpClient) : Esp32Repository {
    override suspend fun getData(): String {
        return try {
            httpClient.get("$BASE_URL/").bodyAsText()  // pedimos la página principal
        } catch (e: Exception) {
            // Retornamos mensaje de error o lanzamos
            throw e
        }
    }

    override suspend fun bombOn() {
        httpClient.get("${BASE_URL}/on")
    }

    override suspend fun bombOff() {
        httpClient.get("${BASE_URL}/off")
    }
}