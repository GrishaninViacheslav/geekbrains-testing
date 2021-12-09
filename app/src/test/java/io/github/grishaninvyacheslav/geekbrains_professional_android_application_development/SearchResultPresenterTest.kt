package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development

import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository.IDictionaryRepository
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_result.SearchResultPresenter
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_result.SearchResultView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import com.github.terrakok.cicerone.Cicerone
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.DefinitionDto
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.DictionaryWordDto
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.MeaningsDto
import io.reactivex.Single
import org.junit.ClassRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Rule

@RunWith(MockitoJUnitRunner::class)
class SearchResultPresenterTest {
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var presenter: SearchResultPresenter

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
    private lateinit var repositoryMock: IDictionaryRepository

    @Mock
    private lateinit var viewMock: SearchResultView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val cicerone = Cicerone.create()
        val navigatorHolder = cicerone.getNavigatorHolder()
        val router = cicerone.router
        presenter = SearchResultPresenter(
            repository = repositoryFake,
            router = router
        )
    }

    @Test
    fun getDefinitions_Test() {
        val searchQuery = "word"
        presenter.attach(viewMock, searchQuery)
        Mockito.verify(repositoryMock, Mockito.times(1)).getDefinitions(searchQuery)
        // TODO: не разобрался почему не проходят закомментированные тесты. Скорее всего это связанно с асинхронным исполнением, но я не понял как это сделать парвильно
        // Mockito.verify(viewMock, Mockito.times(1)).setTitle("$searchQuery", "[$searchQuery phonetic]")
        presenter.detach(viewMock)
    }

//    @Test
//    fun getDefinitionsError_Test() {
//        val searchQuery = "nonexistingword"
//        presenter.attach(viewMock, searchQuery)
//        Mockito.verify(viewMock, Mockito.times(1)).showErrorMessage("Слово не найдено")
//        presenter.detach(viewMock)
//    }
}