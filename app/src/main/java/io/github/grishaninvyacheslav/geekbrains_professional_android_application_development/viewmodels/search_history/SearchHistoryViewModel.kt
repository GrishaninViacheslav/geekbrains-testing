package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App

class SearchHistoryViewModel(
    private val router: Router = App.instance.router
) : ViewModel() {
    private val _liveData = MutableLiveData<HistoryScreenState>()
    private val liveData: LiveData<HistoryScreenState> = _liveData

    fun getLiveHistory() = liveData

    var isLoadSearchHistoryWasCalledFromTheLastTime = false
        private set
        get() {
            val lastTimeValue = field
            field = false
            return lastTimeValue
        }

    fun loadSearchHistory() {
        isLoadSearchHistoryWasCalledFromTheLastTime = true
        // Так как данный функционал используется только для тестирования, то вместо
        // настоящего репозитория, возразающего observable и извлекающего данные асинхронно,
        // используется это:
        _liveData.value = HistoryScreenState.Loading
        val history = List(10) { index ->
            "[ITEM_${index + 1}]"
        }
        _liveData.value = HistoryScreenState.DisplayingHistory(history)
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