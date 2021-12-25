package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development

import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository.IDictionaryRepository
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_result.SearchResultViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.RouterStub
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.DefinitionDto
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.DictionaryWordDto
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.MeaningsDto
import io.reactivex.Single
import org.junit.ClassRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Rule

@RunWith(MockitoJUnitRunner::class)
class SearchResultViewModelTest {
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: SearchResultViewModel

    private var repositoryFake: IDictionaryRepository = object : IDictionaryRepository {
        override fun getDefinitions(word: String): Single<List<DictionaryWordDto>> {
            repositoryMock.getDefinitions(word)
            return Single.just(
                listOf(
                    DictionaryWordDto(
                        word, "[$word phonetic]", listOf(
                            MeaningsDto(
                                "$word partOfSpeech", listOf(
                                    DefinitionDto("$word definition", "$word example")
                                )
                            )
                        )
                    )
                )
            )
        }
    }

    @Mock
    private lateinit var routerMock: RouterStub

    @Mock
    private lateinit var repositoryMock: IDictionaryRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = SearchResultViewModel(
            repository = repositoryFake,
            router = routerMock
        )
    }

//    @Test
//    fun getDefinitions_Test() {
//        val searchQuery = "word"
//        viewModel.loadDefinitions(searchQuery)
//        Mockito.verify(repositoryMock, Mockito.times(1)).getDefinitions(searchQuery)
//    }
}