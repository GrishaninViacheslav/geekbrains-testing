package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.di.AppComponent
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.di.AppModule
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.di.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var appComponent: AppComponent
        private set

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}