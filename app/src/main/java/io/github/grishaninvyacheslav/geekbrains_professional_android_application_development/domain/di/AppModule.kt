package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.di

import dagger.Module
import dagger.Provides
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

@Module
class AppModule(val app: App) {

    @Provides
    fun provideApp(): App {
        return app
    }

    @Provides
    fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}