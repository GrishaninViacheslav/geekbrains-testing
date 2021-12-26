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
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
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
    fun isViewModelLoadsDefinitions(){
        scenario.onFragment { fragment ->
            with(fragment.viewModel) {
                assertNotNull(this)
                assertTrue(isLoadDefinitionsWasCalledFromTheLastTime)
            }
        }
    }

    @Test
    fun isViewModelDisposesResources(){
        scenario.onFragment { fragment ->
            with(fragment.viewModel) {
                assertNotNull(this)
                fragment.onDestroy()
                assertTrue(isDisposeResourcesWasCalledFromTheLastTime)
            }
        }
    }

    @Test
    fun isViewModelBackPresses(){
        scenario.onFragment { fragment ->
            with(fragment.viewModel) {
                assertNotNull(this)
                fragment.backPressed()
                assertTrue(isBackPressedWasCalledFromTheLastTime)
            }
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