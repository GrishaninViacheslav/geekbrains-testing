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
class SearchResultFragmentEspressoTest {

    lateinit var scenario: FragmentScenario<SearchResultFragment>

    private val query = "word"

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(fragmentArgs = Bundle().apply {
            putString(
                SearchResultFragment.QUERY_ARG,
                query
            )
        }, themeResId = R.style.Theme_Geekbrainsprofessionalandroidapplicationdevelopment)
//        {
        // TODO: почему эта фабрика теряет аргументы?
        //              https://github.com/android/android-test/issues/442
//            SearchResultFragment.newInstance("word")
//        }
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
            .check(ViewAssertions.matches(ViewMatchers.withText(query)))
        Espresso.onView(ViewMatchers.withId(R.id.phonetic))
            .check(ViewAssertions.matches(ViewMatchers.withText("[$query]")))
    }
}