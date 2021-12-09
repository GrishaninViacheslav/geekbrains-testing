package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters

interface PresenterContract<T : ViewContract> {
    fun attach(view: T)
    fun detach(view: T)
}