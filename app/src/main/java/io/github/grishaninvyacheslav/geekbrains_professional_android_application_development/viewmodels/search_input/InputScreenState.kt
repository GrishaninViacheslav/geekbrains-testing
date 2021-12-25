package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_input

sealed class InputScreenState {
    object Success : InputScreenState()
    data class Error(val error: Throwable) : InputScreenState()
}