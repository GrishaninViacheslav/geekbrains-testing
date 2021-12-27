package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_result

import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.DictionaryWordDto

sealed class ResultScreenState {
    object Loading : ResultScreenState()
    data class Success(val dictionaryWordDto: DictionaryWordDto) : ResultScreenState()
    data class Error(val error: Throwable) : ResultScreenState()
}