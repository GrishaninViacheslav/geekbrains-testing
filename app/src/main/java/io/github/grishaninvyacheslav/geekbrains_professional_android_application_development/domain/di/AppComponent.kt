package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.di

import dagger.Component
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.activity.MainActivity
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.fragments.SearchHistoryFragment
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.fragments.SearchInputFragment
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.fragments.SearchResultFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RepositoriesModule::class,
        ViewModelsFactoriesModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(searchInputFragment: SearchInputFragment)
    fun inject(searchResultFragment: SearchResultFragment)
    fun inject(searchHistoryFragment: SearchHistoryFragment)
}