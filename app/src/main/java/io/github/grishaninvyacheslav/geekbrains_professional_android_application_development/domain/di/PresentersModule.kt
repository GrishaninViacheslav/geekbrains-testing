package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.di

import dagger.Module
import dagger.Provides
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.main.MainPresenter
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_input.SearchInputPresenter
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_result.SearchResultPresenter

@Module
class PresentersModule {
    @Provides
    fun providesMainPresenter(): MainPresenter {
        return MainPresenter()
    }

    @Provides
    fun providesSearchInputPresenter(): SearchInputPresenter {
        return SearchInputPresenter()
    }

    @Provides
    fun providesSearchResultPresenter(): SearchResultPresenter {
        return SearchResultPresenter()
    }
}