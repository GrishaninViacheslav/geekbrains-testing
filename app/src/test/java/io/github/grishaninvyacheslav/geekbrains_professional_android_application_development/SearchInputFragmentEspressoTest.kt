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


    // TODO: не разобрался как получить состояние фрагмента при использовании FragmentScenario
//    @Test
//    fun fragment_IsResumed() {
//        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
//    }

    @Test
    fun fragmentBasicViews_IsCompletelyDisplayed() {
        onView(withId(R.id.search_input)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.search_confirm)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun emptyQueryErrorMessageView_IsWorking() {
        onView(withId(R.id.search_confirm)).perform(click())
        onView(withId(R.id.error_message)).check(matches(withText("Запрос не может быть пустым")))
    }

    @Test
    fun moreThenOneWordQueryErrorMessageView_IsWorking() {
        onView(withId(R.id.search_input)).perform(click())
        onView(withId(R.id.search_input)).perform(replaceText("more then one word"), closeSoftKeyboard())
        onView(withId(R.id.search_confirm)).perform(click())
        onView(withId(R.id.error_message)).check(matches(withText("Запрос должен состоять тольо из одного слова")))
    }
}