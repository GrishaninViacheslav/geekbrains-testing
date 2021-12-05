package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun searchInput(): Screen
    fun searchResult(searchQuery: String): Screen
}