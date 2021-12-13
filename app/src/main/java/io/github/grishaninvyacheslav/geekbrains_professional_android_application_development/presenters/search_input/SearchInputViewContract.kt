package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_input

import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.ViewContract

interface SearchInputViewContract : ViewContract {
    fun showMessage(message: String)
}