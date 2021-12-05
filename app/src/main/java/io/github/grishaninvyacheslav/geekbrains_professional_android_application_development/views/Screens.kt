package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views

import com.github.terrakok.cicerone.androidx.FragmentScreen
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.fragments.SearchInputFragment
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.fragments.SearchResultFragment

object Screens : IScreens {
    override fun searchInput() = FragmentScreen { SearchInputFragment.newInstance() }
    override fun searchResult(searchQuery: String) = FragmentScreen { SearchResultFragment.newInstance(searchQuery) }
}