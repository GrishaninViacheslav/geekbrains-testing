package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.databinding.FragmentSearchResultBinding
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.domain.models.repository.IDictionaryRepository
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_result.ResultScreenState
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_result.SearchResultViewModel
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_result.SearchResultViewModelFactory
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.BackButtonListener
import javax.inject.Inject

class SearchResultFragment : Fragment(), BackButtonListener {
    private var _view: FragmentSearchResultBinding? = null
    private val view get() = _view!!

    private fun setTitle(wordValue: String, phoneticValue: String) = with(view) {
        word.text = wordValue
        phonetic.text = phoneticValue
        setProgressBarVisibility(false)
    }

    private fun showErrorMessage(message: String) = with(view.errorMessage) {
        text = message
        visibility = VISIBLE
    }

    private fun setProgressBarVisibility(isVisible: Boolean) = with(view) {
        progressBar.isVisible = isVisible
        errorMessage.isVisible = isVisible
    }

    @Inject
    lateinit var viewModelFactory: SearchResultViewModelFactory

    val viewModel: SearchResultViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(SearchResultViewModel::class.java)
    }

    companion object {
        val QUERY_ARG = "QUERY"

        @JvmStatic
        fun newInstance(query: String) = SearchResultFragment().apply {
            arguments = Bundle().apply { putString(QUERY_ARG, query) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _view = FragmentSearchResultBinding.inflate(inflater, container, false)
        return view.root.apply {
            viewModel.getLiveResult().observe(viewLifecycleOwner) {
                when (it) {
                    is ResultScreenState.Success -> setTitle(
                        it.dictionaryWordDto.word,
                        it.dictionaryWordDto.phonetic
                    )
                    is ResultScreenState.Loading -> setProgressBarVisibility(true)
                    is ResultScreenState.Error -> showErrorMessage(
                        it.error.message ?: "Произошла ошибка"
                    )
                }
            }
            viewModel.loadDefinitions(requireArguments().getString(QUERY_ARG)!!)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposeResources()
        _view = null
    }

    override fun backPressed() = viewModel.backPressed()
}