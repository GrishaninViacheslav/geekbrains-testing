package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository

import io.reactivex.Single

class SearchHistoryRepositoryFake : ISearchHistoryRepository {
    override fun getHistory() = Single.just(
        List(20) { index ->
            "[ITEM_${index + 1}]"
        })
}