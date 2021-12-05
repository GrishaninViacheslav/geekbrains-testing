package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.any
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.RouterStub
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.main.MainPresenter
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.main.MainView
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var presenter: MainPresenter

    @Mock
    private lateinit var viewMock: MainView

    @Mock
    private lateinit var routerMock: RouterStub

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(
            router = routerMock
        )
    }

    @Test
    fun init_Test() {
        presenter.attach(viewMock)
        Mockito.verify(routerMock, Mockito.times(1))._replaceScreen(any())
        presenter.detach(viewMock)
    }
}