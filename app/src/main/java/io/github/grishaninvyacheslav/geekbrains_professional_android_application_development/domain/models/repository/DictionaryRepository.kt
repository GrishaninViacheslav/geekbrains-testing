package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository

import io.reactivex.schedulers.Schedulers

class DictionaryRepository(private val api: IDataSource) : IDictionaryRepository {
    override fun getDefinitions(word: String) =
        api.getDefinitions(word).subscribeOn(Schedulers.io())
}