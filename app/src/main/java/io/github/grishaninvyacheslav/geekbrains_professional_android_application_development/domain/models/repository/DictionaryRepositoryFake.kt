package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository

import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.DefinitionDto
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.DictionaryWordDto
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.MeaningsDto
import io.reactivex.Single

class DictionaryRepositoryFake(private val api: IDataSource) : IDictionaryRepository {
    override fun getDefinitions(word: String) =
        Single.just(
            listOf(
                DictionaryWordDto(
                    word = word,
                    phonetic = "[$word]",
                    meanings = listOf(
                        MeaningsDto(
                            partOfSpeech = "$word part of speech",
                            definitions = listOf(
                                DefinitionDto(
                                    definition = "$word definition",
                                    example = "$word example"
                                )
                            )
                        )
                    )
                )
            )
        )
}