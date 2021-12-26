package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development

import android.os.Build
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository.IDictionaryRepository
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_result.SearchResultViewModel
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.RouterStub
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.DefinitionDto
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.DictionaryWordDto
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.MeaningsDto
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_result.ResultScreenState
import io.reactivex.Single
import androidx.lifecycle.Observer
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.schedulers.StubSchedulers
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito
import org.robolectric.annotation.Config
import org.junit.*

@RunWith(MockitoJUnitRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class SearchResultViewModelTest {
    @get:Rule
    var instantExecutorRule = androidx.arch.core.executor.testing.InstantTaskExecutorRule()

    private lateinit var viewModel: SearchResultViewModel

    @Mock
    private lateinit var routerMock: RouterStub

    @Mock
    private lateinit var repositoryMock: IDictionaryRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = SearchResultViewModel(
            repository = repositoryMock,
            router = routerMock,
            schedulers = StubSchedulers
        )
    }

    @Test
    fun getDefinitions_Test() {
        val observer = Observer<ResultScreenState> {}
        val liveResult = viewModel.getLiveResult()
        Mockito.`when`(repositoryMock.getDefinitions(VALID_QUERY)).thenReturn(
            Single.just(
                listOf(
                    DictionaryWordDto(
                        VALID_QUERY, "[$VALID_QUERY phonetic]", listOf(
                            MeaningsDto(
                                "$VALID_QUERY partOfSpeech", listOf(
                                    DefinitionDto("$VALID_QUERY definition", "$VALID_QUERY example")
                                )
                            )
                        )
                    )
                )
            )
        )

        try {
            liveResult.observeForever(observer)
            viewModel.loadDefinitions(VALID_QUERY)
            Assert.assertNotNull(liveResult.value)
        } finally {
            liveResult.removeObserver(observer)
        }
    }
}