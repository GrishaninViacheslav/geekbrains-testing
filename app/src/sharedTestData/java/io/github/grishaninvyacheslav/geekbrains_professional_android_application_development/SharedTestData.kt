package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development

import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.DefinitionDto
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.MeaningsDto

internal const val EMPTY_QUERY_ERROR_MESSAGE = "Запрос не может быть пустым"
internal const val MORE_THEN_ONE_WORD_QUERY = "more then one word"
internal const val MORE_THEN_ONE_WORD_QUERY_ERROR_MESSAGE = "Запрос должен состоять тольо из одного слова"
internal const val VALID_QUERY = "word"
internal val VALID_QUERY_MEANINGS = listOf(
    MeaningsDto(
        "$VALID_QUERY partOfSpeech", listOf(
            DefinitionDto("$VALID_QUERY definition", "$VALID_QUERY example")
        )
    )
)
internal const val EMPTY_QUERY = ""