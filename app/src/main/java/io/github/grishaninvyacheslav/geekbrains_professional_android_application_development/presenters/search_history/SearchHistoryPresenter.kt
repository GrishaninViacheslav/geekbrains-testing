package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_history

import com.github.terrakok.cicerone.Router
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.MvpPresenter

class SearchHistoryPresenter(
    private val router: Router = App.instance.router
) :
    MvpPresenter<SearchHistoryViewContract>(), SearchHistoryPresenterContract {
    override fun loadSearchHistory() {
        // Так как данный функционал используется только для тестирования, то вместо
        // настоящего репозитория, возразающего observable и извлекающего данные асинхронно,
        // используется это:
        val history = List(10) { index ->
            "[ITEM_${index + 1}]"
        }
        forEachView { it.initHistoryList(history) }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}