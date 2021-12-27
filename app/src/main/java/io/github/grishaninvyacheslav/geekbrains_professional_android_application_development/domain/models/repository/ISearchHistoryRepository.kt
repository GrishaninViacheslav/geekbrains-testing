package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository

interface ISearchHistoryRepository {
    suspend fun getHistory(): List<String>
}