package uabjo.drti.eleccion

import android.app.Application
import android.util.Log
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import uabjo.drti.eleccion.modules.common.framework.di.coreModule
import uabjo.drti.eleccion.modules.elections.framework.di.candidatesModule

class Eleccion: Application() {

    override fun onCreate() {
        Log.d("APP", "En ejecución")
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            androidContext(this@Eleccion)
            modules(arrayListOf(coreModule, candidatesModule))
        }
    }
}
