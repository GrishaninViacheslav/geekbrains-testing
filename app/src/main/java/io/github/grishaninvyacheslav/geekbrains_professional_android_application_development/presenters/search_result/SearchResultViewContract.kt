package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_result

import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.ViewContract

interface SearchResultViewContract : ViewContract {
    fun setTitle(wordValue: String, phoneticValue: String)
    fun showErrorMessage(message: String)
}