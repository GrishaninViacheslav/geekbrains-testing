package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_input

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.R
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.MvpPresenter
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.RouterStub
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.Screens

class SearchInputPresenter(
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
) :
    MvpPresenter<SearchInputView>() {
    private fun validate(query: String) =
        (!query.contains(" ") && query.isNotBlank()).also { isValid ->
            if (!isValid) {
                if (query.contains(" ")) {
                    forEachView { it.showMessage("Запрос должен состоять тольо из одного слова") }
                } else if (query.isBlank()) {
                    forEachView { it.showMessage("Запрос не может быть пустым") }
                }
            }
        }

    fun submitQuery(query: String) {
        if (validate(query)) {
            router._navigateTo(Screens.searchResult(query))
        }
    }

    fun backPressed(): Boolean {
        router._exit()
        return true
    }
}