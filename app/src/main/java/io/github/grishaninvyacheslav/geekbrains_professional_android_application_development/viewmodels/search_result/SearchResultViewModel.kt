package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.R
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.DictionaryWordDto
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository.IDictionaryRepository
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.schedulers.DefaultSchedulers
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.RouterStub
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.schedulers.ISchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

class SearchResultViewModel(
    var repository: IDictionaryRepository,
    private val router: RouterStub
) : ViewModel() {
    private var disposables: CompositeDisposable = CompositeDisposable()
    private val schedulers: ISchedulers = DefaultSchedulers

    private val _liveData = MutableLiveData<ResultScreenState>()
    private val liveData: LiveData<ResultScreenState> = _liveData

    private inner class DefinitionsLoadObserver :
        DisposableSingleObserver<List<DictionaryWordDto>>() {
        override fun onSuccess(value: List<DictionaryWordDto>) {
            _liveData.value = ResultScreenState.Success(value[0])
        }

        override fun onError(error: Throwable) {
            error.printStackTrace()
            when (error) {
                is java.net.UnknownHostException ->
                    _liveData.value = ResultScreenState.Error(Throwable(App.instance.getString(R.string.error_500)))
                is retrofit2.adapter.rxjava2.HttpException ->
                    _liveData.value = ResultScreenState.Error(Throwable(App.instance.getString(R.string.error_404)))
            }
        }
    }

    fun getLiveResult() = liveData

    var isLoadDefinitionsWasCalledFromTheLastTime = false
        private set
        get() {
            val lastTimeValue = field
            field = false
            return lastTimeValue
        }

    fun loadDefinitions(query: String) {
        isLoadDefinitionsWasCalledFromTheLastTime = true
        val getDefinitionsRequest = repository.getDefinitions(query)
        disposables.add(
            getDefinitionsRequest.observeOn(schedulers.main())
                .subscribeWith(DefinitionsLoadObserver())
        )
    }

    var isDisposeResourcesWasCalledFromTheLastTime = false
        private set
        get() {
            val lastTimeValue = field
            field = false
            return lastTimeValue
        }

    fun disposeResources() {
        isDisposeResourcesWasCalledFromTheLastTime = true
        disposables.dispose()
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