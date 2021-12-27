package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.RouterStub
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.Screens

class MainViewModel(
    private val router: RouterStub = object : RouterStub {
        private val router: Router = App.instance.router

        override fun navigateTo(screen: Screen, clearContainer: Boolean) =
            this.router.navigateTo(screen, clearContainer)

        override fun newRootScreen(screen: Screen) =
            this.router.newRootScreen(screen)

        override fun replaceScreen(screen: Screen) =
            this.router.replaceScreen(screen)

        override fun backTo(screen: Screen?) =
            this.router.backTo(screen)


        override fun newChain(vararg screens: Screen, showOnlyTopScreenView: Boolean) =
            this.router.newChain(screens = screens, showOnlyTopScreenView = showOnlyTopScreenView)


        override fun newRootChain(vararg screens: Screen, showOnlyTopScreenView: Boolean) =
            this.router.newRootChain(
                screens = screens,
                showOnlyTopScreenView = showOnlyTopScreenView
            )

        override fun finishChain() = this.router.finishChain()

        override fun exit() = this.router.exit()
    }
) : ViewModel() {
    var isInitWasCalledFromTheLastTime = false
        private set
        get() {
            val lastTimeValue = field
            field = false
            return lastTimeValue
        }

    fun init() {
        isInitWasCalledFromTheLastTime = true
        router.replaceScreen(Screens.searchInput())
    }

    var isBackPressedWasCalledFromTheLastTime = false
        private set
        get() {
            val lastTimeValue = field
            field = false
            return lastTimeValue
        }

    fun backPressed(): Boolean {
        isBackPressedWasCalledFromTheLastTime = true
        router.exit()
        return true
    }
}