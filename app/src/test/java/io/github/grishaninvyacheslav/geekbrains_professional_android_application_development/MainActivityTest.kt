package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.activity.MainActivity
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class MainActivityTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    private lateinit var context: Context

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activityContainer_NotNull() {
        scenario.onActivity {
            val container = it.findViewById<FrameLayout>(R.id.container)
            assertNotNull(container)
        }
    }

    @Test
    fun activityContainer_IsVisible() {
        scenario.onActivity {
            val container = it.findViewById<FrameLayout>(R.id.container)
            assertEquals(View.VISIBLE, container.visibility)
        }
    }

    @Test
    fun activityCreateIntent_NotNull() {
        val intent = MainActivity.getIntent(context)
        assertNotNull(intent)
    }

    @Test
    fun isViewModelInitializes() {
        scenario.onActivity { activity ->
            with(activity.viewModel) {
                assertNotNull(this)
                assertTrue(isInitWasCalledFromTheLastTime)
            }
        }
    }

    @Test
    fun isViewModelBackPressedCalls() {
        scenario.onActivity { activity ->
            with(activity.viewModel) {
                assertNotNull(this)
                activity.backPressed()
                assertTrue(isBackPressedWasCalledFromTheLastTime)
            }
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}