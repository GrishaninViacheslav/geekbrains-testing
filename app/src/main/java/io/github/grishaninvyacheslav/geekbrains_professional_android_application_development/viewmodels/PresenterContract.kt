package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels

interface PresenterContract<T : ViewContract> {
    fun attach(view: T)
    fun detach(view: T)
    fun getAttachedViews(): ArrayList<T>
}