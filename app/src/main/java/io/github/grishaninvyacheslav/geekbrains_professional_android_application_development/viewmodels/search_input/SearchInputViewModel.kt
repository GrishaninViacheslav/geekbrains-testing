package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_input

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.RouterStub
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.Screens

class SearchInputViewModel(
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
) : ViewModel() {
    private val _liveData = MutableLiveData<InputScreenState>()
    private val liveData: LiveData<InputScreenState> = _liveData

    private fun validate(query: String) =
        (!query.contains(" ") && query.isNotBlank()).also { isValid ->
            if (!isValid) {
                if (query.contains(" ")) {
                    _liveData.value = InputScreenState.Error(Throwable("Запрос должен состоять тольо из одного слова"))
                } else if (query.isBlank()) {
                    _liveData.value = InputScreenState.Error(Throwable("Запрос не может быть пустым"))
                }
            }
        }

    fun getLiveValidation() = liveData

    var isSubmitQueryWasCalledFromTheLastTime = false
        private set
        get() {
            val lastTimeValue = field
            field = false
            return lastTimeValue
        }

    fun submitQuery(query: String) {
        isSubmitQueryWasCalledFromTheLastTime = true
        if (validate(query)) {
            _liveData.value = InputScreenState.Success
            router._navigateTo(Screens.searchResult(query))
        }
    }

    var isOpenHistoryWasCalledFromTheLastTime = false
        private set
        get() {
            val lastTimeValue = field
            field = false
            return lastTimeValue
        }

    fun openHistory() {
        isOpenHistoryWasCalledFromTheLastTime = true
        router._navigateTo(Screens.searchHistory())
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
        router._exit()
        return true
    }
}