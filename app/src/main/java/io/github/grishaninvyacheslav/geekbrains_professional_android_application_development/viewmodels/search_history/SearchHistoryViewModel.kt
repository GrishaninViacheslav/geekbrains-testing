package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.RouterStub
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository.ISearchHistoryRepository
import kotlinx.coroutines.*

class SearchHistoryViewModel(
    private var repository: ISearchHistoryRepository,
    private val router: RouterStub,
) : ViewModel() {
    private val _liveData = MutableLiveData<HistoryScreenState>()
    private val liveData: LiveData<HistoryScreenState> = _liveData

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    private fun handleError(error: Throwable) {
        error.printStackTrace()
        _liveData.value = HistoryScreenState.Error(error)
    }

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
        _liveData.value = HistoryScreenState.Loading
        viewModelCoroutineScope.launch {
            _liveData.value = HistoryScreenState.DisplayingHistory(repository.getHistory())
        }
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