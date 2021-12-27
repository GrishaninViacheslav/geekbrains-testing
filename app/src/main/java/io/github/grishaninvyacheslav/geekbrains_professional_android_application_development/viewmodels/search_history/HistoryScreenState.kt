package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_history

sealed class HistoryScreenState {
    object Loading : HistoryScreenState()
    data class DisplayingHistory(val history:  List<String>) : HistoryScreenState()
    data class Error(val error: Throwable) : HistoryScreenState()
}