package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.activity


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// Решение задания 2 - класс сгенерированный с помощью Test Recorder’а.
@LargeTest
@RunWith(AndroidJUnit4::class)
class SearchInputErrorTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun searchInputErrorTest() {
        // Получение view кнопки подтверждения
        val materialButton = onView(
            allOf(
                withId(R.id.search_confirm),
                withText("�����"),
                childAtPosition(childAtPosition(withId(R.id.container), 0), 2),
                isDisplayed()
            )
        )
        // Нажатие на кнопку подтверждения
        materialButton.perform(click())

        // Получение view с сообщением ошибки
        val textView = onView(
            allOf(
                withId(R.id.error_message), withText("������ �� ����� ���� ������"),
                withParent(withParent(withId(R.id.container))),
                isDisplayed()
            )
        )
        // Проверка на свопадение текста сообщения ошибки
        textView.check(matches(withText("������ �� ����� ���� ������")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
