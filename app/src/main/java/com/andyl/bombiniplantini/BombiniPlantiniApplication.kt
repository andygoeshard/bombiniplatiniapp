package com.andyl.bombiniplantini

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.andyl.bombiniplantini.core.di.networkModule
import com.andyl.bombiniplantini.core.di.repositoryModule
import com.andyl.bombiniplantini.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class BombiniPlantiniApplication: Application() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@BombiniPlantiniApplication)
            modules(
                networkModule,
                viewModelModule,
                repositoryModule,
            )
        }
    }

}