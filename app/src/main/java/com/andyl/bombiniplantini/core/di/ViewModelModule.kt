package com.andyl.bombiniplantini.core.di

import com.andyl.bombiniplantini.presentation.viewmodel.RiegoViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val viewModelModule = module{
    viewModel { RiegoViewModel(get()) }
}