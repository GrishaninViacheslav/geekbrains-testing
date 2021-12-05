package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain

import com.github.terrakok.cicerone.Screen

interface RouterStub {
    fun _navigateTo(screen: Screen, clearContainer: Boolean = true)
    fun _newRootScreen(screen: Screen)
    fun _replaceScreen(screen: Screen)
    fun _backTo(screen: Screen?)
    fun _newChain(vararg screens: Screen, showOnlyTopScreenView: Boolean = true)
    fun _newRootChain(vararg screens: Screen, showOnlyTopScreenView: Boolean = true)
    fun _finishChain()
    fun _exit()
}