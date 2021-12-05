package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_input

import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.View

interface SearchInputView : View {
    fun showMessage(message: String)
}