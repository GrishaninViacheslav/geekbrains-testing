package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.databinding.FragmentSearchResultBinding
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_result.SearchResultPresenter
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_result.SearchResultViewContract
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.BackButtonListener
import javax.inject.Inject

class SearchResultFragment : Fragment(), SearchResultViewContract, BackButtonListener {
    private var _view: FragmentSearchResultBinding? = null
    private val view get() = _view!!

    @Inject
    lateinit var presenter: SearchResultPresenter

    companion object {
        val QUERY_ARG = "QUERY"

        @JvmStatic
        fun newInstance(query: String) = SearchResultFragment().apply {
            arguments = Bundle().apply { putString(QUERY_ARG, query) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
        presenter.attach(this, requireArguments().getString(QUERY_ARG)!!)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _view = FragmentSearchResultBinding.inflate(inflater, container, false)
        return view.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _view = null
        presenter.detach(this)
    }

    override fun setTitle(wordValue: String, phoneticValue: String) = with(view) {
        word.text = wordValue
        phonetic.text = phoneticValue
        progressBar.visibility = GONE
        errorMessage.visibility = GONE
    }

    override fun showErrorMessage(message: String) = with(view.errorMessage) {
        text = message
        visibility = VISIBLE
    }

    override fun backPressed() = presenter.backPressed()
}