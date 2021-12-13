package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_result

import com.github.terrakok.cicerone.Router
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.R
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.DictionaryWordDto
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository.DictionaryRepository
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository.IDictionaryRepository
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.schedulers.DefaultSchedulers
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.MvpPresenter
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.ApiHolder
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.schedulers.ISchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

class SearchResultPresenter(
    private var query: String = "",
    private var disposables: CompositeDisposable = CompositeDisposable(),
    private val repository: IDictionaryRepository = DictionaryRepository(ApiHolder.api),
    private val router: Router = App.instance.router,
    private val schedulers: ISchedulers = DefaultSchedulers
) :
    MvpPresenter<SearchResultViewContract>(), SearchResultPresenterContract {
    private inner class DefinitionsLoadObserver :
        DisposableSingleObserver<List<DictionaryWordDto>>() {
        override fun onSuccess(value: List<DictionaryWordDto>) {
            forEachView { it.setTitle(value[0].word, value[0].phonetic) }
        }

        override fun onError(error: Throwable) {
            error.printStackTrace()
            when (error) {
                is java.net.UnknownHostException ->
                    forEachView { it.showErrorMessage(App.instance.getString(R.string.error_500)) }
                is retrofit2.adapter.rxjava2.HttpException ->
                    forEachView { it.showErrorMessage(App.instance.getString(R.string.error_404)) }
            }
        }
    }

    private fun loadDefinitions() {
        val getDefinitionsRequest = repository.getDefinitions(query)
        disposables.add(
            getDefinitionsRequest.observeOn(schedulers.main())
                .subscribeWith(DefinitionsLoadObserver())
        )
    }

    fun attach(view: SearchResultViewContract, query: String) {
        this.query = query
        super.attach(view)
    }

    override fun onFirstViewAttach() {
        loadDefinitions()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}