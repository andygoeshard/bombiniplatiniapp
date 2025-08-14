package com.andyl.bombiniplantini.core.di

import com.andyl.bombiniplantini.data.repository.Esp32RepositoryImpl
import com.andyl.bombiniplantini.domain.repository.Esp32Repository
import org.koin.dsl.module

val repositoryModule = module {
    single <Esp32Repository> { Esp32RepositoryImpl(get()) }
}