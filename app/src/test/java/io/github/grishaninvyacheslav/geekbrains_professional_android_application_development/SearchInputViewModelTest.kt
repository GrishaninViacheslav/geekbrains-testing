package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development

import android.os.Build
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.RouterStub
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import androidx.lifecycle.Observer
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_input.InputScreenState
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_input.SearchInputViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config

@RunWith(MockitoJUnitRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class SearchInputViewModelTest {
    @get:Rule
    var instantExecutorRule = androidx.arch.core.executor.testing.InstantTaskExecutorRule()

    private lateinit var viewModel: SearchInputViewModel

    @Mock
    private lateinit var routerMock: RouterStub

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = SearchInputViewModel(routerMock)
    }

    @Test
    fun submitQuery_Test() {
        val observer = Observer<InputScreenState> {}
        val liveResult = viewModel.getLiveValidation()
        try {
            liveResult.observeForever(observer)
            viewModel.submitQuery(VALID_QUERY)
            Assert.assertNotNull(liveResult.value)
        } finally {
            liveResult.removeObserver(observer)
        }
    }

    @Test
    fun submitValidQuery_Test() {
        val observer = Observer<InputScreenState> {}
        val liveResult = viewModel.getLiveValidation()
        try {
            liveResult.observeForever(observer)
            viewModel.submitQuery(VALID_QUERY)
            assertTrue(liveResult.value is InputScreenState.Success)
        } finally {
            liveResult.removeObserver(observer)
        }
    }

    @Test
    fun submitQueryMoreThenOneWordError_Test() {
        val observer = Observer<InputScreenState> {}
        val liveResult = viewModel.getLiveValidation()
        try {
            liveResult.observeForever(observer)
            viewModel.submitQuery(MORE_THEN_ONE_WORD_QUERY)
            assertTrue(liveResult.value is InputScreenState.Error)
            if (liveResult.value is InputScreenState.Error) {
                assertEquals(
                    (liveResult.value as InputScreenState.Error).error.message,
                    "Запрос должен состоять тольо из одного слова"
                )
            }
        } finally {
            liveResult.removeObserver(observer)
        }
    }

    @Test
    fun submitQueryEmptyError_Test() {
        val observer = Observer<InputScreenState> {}
        val liveResult = viewModel.getLiveValidation()
        try {
            liveResult.observeForever(observer)
            viewModel.submitQuery(EMPTY_QUERY)
            assertTrue(liveResult.value is InputScreenState.Error)
            if (liveResult.value is InputScreenState.Error) {
                assertEquals(
                    (liveResult.value as InputScreenState.Error).error.message,
                    "Запрос не может быть пустым"
                )
            }
        } finally {
            liveResult.removeObserver(observer)
        }
    }
}