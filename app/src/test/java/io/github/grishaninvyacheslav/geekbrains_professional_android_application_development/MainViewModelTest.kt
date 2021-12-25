package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.any
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.RouterStub
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.MainViewModel
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
class MainViewModelTest {
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var routerMock: RouterStub

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(
            router = routerMock
        )
    }

    @Test
    fun init_Test() {
        viewModel.init()
        Mockito.verify(routerMock, Mockito.times(1))._replaceScreen(any())
    }
}