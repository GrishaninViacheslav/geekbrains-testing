package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository

class SearchHistoryRepositoryFake : ISearchHistoryRepository {
    override suspend fun getHistory(): List<String> {
        return List(20) { index ->
            "[ITEM_${index + 1}]"
        }
    }
}