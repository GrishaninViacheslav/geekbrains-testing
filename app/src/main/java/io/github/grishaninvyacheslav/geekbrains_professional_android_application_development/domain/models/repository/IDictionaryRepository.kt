package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository

import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.DictionaryWordDto
import io.reactivex.Single

interface IDictionaryRepository {
    fun getDefinitions(word: String): Single<List<DictionaryWordDto>>
}