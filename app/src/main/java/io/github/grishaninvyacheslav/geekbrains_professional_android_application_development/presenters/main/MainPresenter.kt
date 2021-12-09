package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.main

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.MvpPresenter
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.RouterStub
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.Screens

class MainPresenter(
    // TODO: выглядет очень странно, есть ли более удобный способ мокать Router?
    private val router: RouterStub = object : RouterStub {
        private val router: Router = App.instance.router

        override fun _navigateTo(screen: Screen, clearContainer: Boolean) =
            this.router.navigateTo(screen, clearContainer)

        override fun _newRootScreen(screen: Screen) =
            this.router.newRootScreen(screen)

        override fun _replaceScreen(screen: Screen) =
            this.router.replaceScreen(screen)

        override fun _backTo(screen: Screen?) =
            this.router.backTo(screen)


        override fun _newChain(vararg screens: Screen, showOnlyTopScreenView: Boolean) =
            this.router.newChain(screens = screens, showOnlyTopScreenView = showOnlyTopScreenView)


        override fun _newRootChain(vararg screens: Screen, showOnlyTopScreenView: Boolean) =
            this.router.newRootChain(
                screens = screens,
                showOnlyTopScreenView = showOnlyTopScreenView
            )

        override fun _finishChain() = this.router.finishChain()

        override fun _exit() = this.router.exit()
    }
) : MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        router._replaceScreen(Screens.searchInput())
    }

    fun backPressed(): Boolean {
        router._exit()
        return true
    }
}