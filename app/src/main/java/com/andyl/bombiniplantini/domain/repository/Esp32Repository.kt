package com.andyl.bombiniplantini.domain.repository

interface Esp32Repository {
    suspend fun getData(): String
    suspend fun bombOn()
    suspend fun bombOff()
}