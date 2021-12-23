package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_history

import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.ViewContract

interface SearchHistoryViewContract : ViewContract {
    fun initHistoryList(history: List<String>)
}