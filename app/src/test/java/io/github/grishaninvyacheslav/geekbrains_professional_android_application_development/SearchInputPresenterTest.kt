package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.any
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.RouterStub
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_input.SearchInputPresenter
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_input.SearchInputView
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
    private lateinit var viewMock: SearchInputView

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
    fun submitQuery_Test() {
        presenter.attach(viewMock)
        presenter.submitQuery("word")
        Mockito.verify(routerMock, Mockito.times(1))._navigateTo(any(), any())
        Mockito.verify(viewMock, Mockito.never()).showMessage(anyString())
        presenter.detach(viewMock)
    }

    @Test
    fun submitQueryMoreThenOneWordError_Test() {
        presenter.attach(viewMock)
        presenter.submitQuery("word and another word")
        Mockito.verify(routerMock, Mockito.never())._navigateTo(any(), any())
        Mockito.verify(viewMock, Mockito.times(1)).showMessage(anyString())
        presenter.detach(viewMock)
    }

    @Test
    fun submitQueryEmptyError_Test() {
        presenter.attach(viewMock)
        presenter.submitQuery("")
        Mockito.verify(routerMock, Mockito.never())._navigateTo(any(), any())
        Mockito.verify(viewMock, Mockito.times(1)).showMessage(anyString())
        presenter.detach(viewMock)
    }
}