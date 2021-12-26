package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.R
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.RouterStub
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.DictionaryWordDto
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository.ISearchHistoryRepository
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.schedulers.ISchedulers
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_result.ResultScreenState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

class SearchHistoryViewModel(
    private var repository: ISearchHistoryRepository,
    private val router: RouterStub,
    private val schedulers: ISchedulers
) : ViewModel() {
    private var disposables: CompositeDisposable = CompositeDisposable()

    private val _liveData = MutableLiveData<HistoryScreenState>()
    private val liveData: LiveData<HistoryScreenState> = _liveData

    private inner class HistoryLoadObserver :
        DisposableSingleObserver<List<String>>() {
        override fun onSuccess(value: List<String>) {
            _liveData.value = HistoryScreenState.DisplayingHistory(value)
        }

        override fun onError(error: Throwable) {
            error.printStackTrace()
            _liveData.value = HistoryScreenState.Error(Throwable("Не удалось получить историю запросов"))
        }
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
        disposables.add(
            repository
                .getHistory()
                .observeOn(schedulers.main())
                .subscribeWith(HistoryLoadObserver())
        )
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