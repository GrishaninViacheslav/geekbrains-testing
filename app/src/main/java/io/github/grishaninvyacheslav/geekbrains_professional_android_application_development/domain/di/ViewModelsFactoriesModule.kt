package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.di

import dagger.Module
import dagger.Provides
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository.IDictionaryRepository
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_result.SearchResultViewModelFactory

@Module
class ViewModelsFactoriesModule {
    @Provides
    fun providesSearchResultViewModelFactory(repository: IDictionaryRepository): SearchResultViewModelFactory {
        return SearchResultViewModelFactory(repository)
    }
}