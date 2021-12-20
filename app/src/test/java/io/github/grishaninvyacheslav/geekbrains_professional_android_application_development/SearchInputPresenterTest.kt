package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.any
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.RouterStub
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_input.SearchInputPresenter
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_input.SearchInputViewContract
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchInputPresenterTest {
    private fun submitValidQuery(routerMockInvocationNumber: Int) {
        presenter.submitQuery(VALID_QUERY)
        Mockito.verify(routerMock, Mockito.times(routerMockInvocationNumber))._navigateTo(any(), any())
    }

    private fun submitMoreThenOneWordQuery(viewMockInvocationNumber: Int) {
        presenter.submitQuery(MORE_THEN_ONE_WORD_QUERY)
        Mockito.verify(viewMock, Mockito.times(viewMockInvocationNumber)).showMessage(anyString())
    }

    private fun submitEmptyQuery(viewMockInvocationNumber: Int) {
        presenter.submitQuery(EMPTY_QUERY)
        Mockito.verify(viewMock, Mockito.times(viewMockInvocationNumber)).showMessage(anyString())
    }

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var presenter: SearchInputPresenter

    @Mock
    private lateinit var viewMock: SearchInputViewContract

    @Mock
    private lateinit var routerMock: RouterStub

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchInputPresenter(
            router = routerMock
        )
    }

    @Test
    fun submitValidQuery_Test() {
        presenter.attach(viewMock)
        submitValidQuery(1)
        Mockito.verify(viewMock, Mockito.never()).showMessage(anyString())
        presenter.detach(viewMock)
    }

    @Test
    fun submitQueryMoreThenOneWordError_Test() {
        presenter.attach(viewMock)
        submitMoreThenOneWordQuery(1)
        Mockito.verify(routerMock, Mockito.never())._navigateTo(any(), any())
        presenter.detach(viewMock)
    }

    @Test
    fun submitQueryEmptyError_Test() {
        presenter.attach(viewMock)
        submitEmptyQuery(1)
        Mockito.verify(routerMock, Mockito.never())._navigateTo(any(), any())
        presenter.detach(viewMock)
    }

    @Test
    fun submitQueries_Test() {
        presenter.attach(viewMock)
        submitMoreThenOneWordQuery(1)
        submitEmptyQuery(2)
        submitValidQuery(1)
        presenter.detach(viewMock)
    }
}