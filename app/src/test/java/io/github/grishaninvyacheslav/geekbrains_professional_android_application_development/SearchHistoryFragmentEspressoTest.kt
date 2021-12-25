package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development

import android.os.Build
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_history.HistoryRVAdapter
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.fragments.SearchHistoryFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import junit.framework.TestCase
import org.hamcrest.Matchers.not


@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])

// Решение задания 1 - протестировать фрагмент
class SearchHistoryFragmentEspressoTest {

    lateinit var scenario: FragmentScenario<SearchHistoryFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(
            themeResId = R.style.Theme_Geekbrainsprofessionalandroidapplicationdevelopment,
            initialState = Lifecycle.State.RESUMED
        )
    }

    @Test
    fun fragment_AssertNotNull() {
        scenario.onFragment() {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun searchHistory_PerformClickAtPosition() {
        scenario.onFragment {
            onView(withId(R.id.search_history_rv))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<HistoryRVAdapter.ViewHolder>(
                        0,
                        click()
                    )
                )
            // Проверяет сообщение в Toast: https://stackoverflow.com/a/28606603/11883985
            onView(withText("[ITEM_1]")).inRoot(withDecorView(not((it.requireActivity().window.decorView)))).check(matches(isDisplayed()))
        }
    }
}