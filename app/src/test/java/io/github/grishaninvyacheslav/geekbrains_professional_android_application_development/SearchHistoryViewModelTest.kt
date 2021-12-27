package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development

import android.os.Build
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.RouterStub
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository.ISearchHistoryRepository
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_history.SearchHistoryViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config
import androidx.lifecycle.Observer
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_history.HistoryScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@RunWith(MockitoJUnitRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
@ExperimentalCoroutinesApi
class SearchHistoryViewModelTest {
    @get:Rule
    var instantExecutorRule = androidx.arch.core.executor.testing.InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: SearchHistoryViewModel

    @Mock
    private lateinit var routerMock: RouterStub

    @Mock
    private lateinit var repositoryMock: ISearchHistoryRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = SearchHistoryViewModel(
            repository = repositoryMock,
            router = routerMock,
        )
    }

    @Test
    fun coroutines_loadSearchHistory_Test() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<HistoryScreenState> {}
            val liveResult = viewModel.getLiveHistory()
            Mockito.`when`(repositoryMock.getHistory()).thenReturn(
                List(20) { index ->
                    "[ITEM_${index + 1}]"
                }
            )

            try {
                liveResult.observeForever(observer)
                viewModel.loadSearchHistory()
                Assert.assertNotNull(liveResult.value)
            } finally {
                liveResult.removeObserver(observer)
            }
        }
    }
}