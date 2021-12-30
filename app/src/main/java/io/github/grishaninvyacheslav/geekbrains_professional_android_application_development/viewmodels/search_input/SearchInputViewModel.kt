package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_input

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.QueryValidator
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.RouterStub
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.Screens

class SearchInputViewModel(
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
    private val _liveData = MutableLiveData<InputScreenState>()
    private val liveData: LiveData<InputScreenState> = _liveData

    private fun validate(query: String) =
        (QueryValidator.validate(query)).also { isValid ->
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
            router.navigateTo(Screens.searchResult(query))
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
        router.navigateTo(Screens.searchHistory())
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