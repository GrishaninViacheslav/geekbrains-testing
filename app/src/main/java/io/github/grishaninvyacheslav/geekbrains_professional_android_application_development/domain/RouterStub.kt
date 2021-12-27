package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain

import com.github.terrakok.cicerone.Screen

interface RouterStub {
    fun navigateTo(screen: Screen, clearContainer: Boolean = true)
    fun newRootScreen(screen: Screen)
    fun replaceScreen(screen: Screen)
    fun backTo(screen: Screen?)
    fun newChain(vararg screens: Screen, showOnlyTopScreenView: Boolean = true)
    fun newRootChain(vararg screens: Screen, showOnlyTopScreenView: Boolean = true)
    fun finishChain()
    fun exit()
}