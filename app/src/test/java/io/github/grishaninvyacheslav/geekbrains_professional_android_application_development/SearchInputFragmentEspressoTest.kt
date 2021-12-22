package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development

import android.os.Build
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.fragments.SearchInputFragment
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class SearchInputFragmentEspressoTest {

    lateinit var scenario: FragmentScenario<SearchInputFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_Geekbrainsprofessionalandroidapplicationdevelopment)
    }

    @Test
    fun fragment_AssertNotNull() {
        scenario.onFragment() {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun fragmentBasicViews_IsCompletelyDisplayed() {
        onView(withId(R.id.search_input)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.search_confirm)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun emptyQueryErrorMessageView_IsWorking() {
        onView(withId(R.id.search_confirm)).perform(click())
        onView(withId(R.id.error_message)).check(matches(withText(EMPTY_QUERY_ERROR_MESSAGE)))
    }

    @Test
    fun moreThenOneWordQueryErrorMessageView_IsWorking() {
        onView(withId(R.id.search_input)).perform(click())
        onView(withId(R.id.search_input)).perform(replaceText(MORE_THEN_ONE_WORD_QUERY), closeSoftKeyboard())
        onView(withId(R.id.search_confirm)).perform(click())
        onView(withId(R.id.error_message)).check(matches(withText(MORE_THEN_ONE_WORD_QUERY_ERROR_MESSAGE)))
    }
}