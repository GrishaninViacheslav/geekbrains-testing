package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.fragments.SearchResultFragment
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
// Решение задания 1 - протестировать фрагмент
class SearchResultFragmentEspressoTest {

    lateinit var scenario: FragmentScenario<SearchResultFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(fragmentArgs = Bundle().apply {
            putString(
                SearchResultFragment.QUERY_ARG,
                VALID_QUERY
            )
        }, themeResId = R.style.Theme_Geekbrainsprofessionalandroidapplicationdevelopment)
    }

    @Test
    fun fragment_AssertNotNull() {
        scenario.onFragment() {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun wordView_IsWorking() {
        Espresso.onView(ViewMatchers.withId(R.id.word))
            .check(ViewAssertions.matches(ViewMatchers.withText(VALID_QUERY)))
        Espresso.onView(ViewMatchers.withId(R.id.phonetic))
            .check(ViewAssertions.matches(ViewMatchers.withText("[$VALID_QUERY]")))
    }
}