package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository

import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.DictionaryWordDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IDataSource {
    @GET("/api/v2/entries/en/{word}")
    fun getDefinitions(@Path("word") word: String): Single<List<DictionaryWordDto>>
}