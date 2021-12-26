package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.RouterStub
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository.ISearchHistoryRepository
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.schedulers.DefaultSchedulers
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.schedulers.ISchedulers

class SearchHistoryViewModelFactory(var repository: ISearchHistoryRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            ISearchHistoryRepository::class.java,
            RouterStub::class.java,
            ISchedulers::class.java
        )
            .newInstance(
                repository,
                object :
                    RouterStub {
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
                        this.router.newChain(
                            screens = screens,
                            showOnlyTopScreenView = showOnlyTopScreenView
                        )


                    override fun newRootChain(
                        vararg screens: Screen,
                        showOnlyTopScreenView: Boolean
                    ) =
                        this.router.newRootChain(
                            screens = screens,
                            showOnlyTopScreenView = showOnlyTopScreenView
                        )

                    override fun finishChain() = this.router.finishChain()

                    override fun exit() = this.router.exit()
                },
                DefaultSchedulers
            )
    }
}