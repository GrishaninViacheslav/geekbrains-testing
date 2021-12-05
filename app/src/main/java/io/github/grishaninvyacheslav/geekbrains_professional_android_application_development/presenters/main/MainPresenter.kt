package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.main

import com.github.terrakok.cicerone.Router
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.MvpPresenter
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.Screens

class MainPresenter(
    private val router: Router = App.instance.router,
) : MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        router.replaceScreen(Screens.searchInput())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}