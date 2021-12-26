package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository

import io.reactivex.Single

interface ISearchHistoryRepository {
    fun getHistory(): Single<List<String>>
}