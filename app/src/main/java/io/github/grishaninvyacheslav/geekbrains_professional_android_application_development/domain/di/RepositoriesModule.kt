package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.BuildConfig
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RepositoriesModule {
    @Provides
    fun providesApi(): IDataSource {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        return Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IDataSource::class.java)
    }

    @Provides
    fun providesDictionaryRepository(api: IDataSource): IDictionaryRepository {
        return when (BuildConfig.BUILD_TYPE) {
            "release" -> DictionaryRepository(api)
            "debug" -> DictionaryRepositoryFake(api)
            else -> DictionaryRepository(api)
        }
    }

    @Provides
    fun providesSearchHistoryRepository(): ISearchHistoryRepository {
        return SearchHistoryRepositoryFake()
    }
}