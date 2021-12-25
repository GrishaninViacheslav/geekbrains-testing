package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.databinding.FragmentSearchHistoryBinding
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_history.HistoryRVAdapter
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_history.SearchHistoryPresenter
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_history.SearchHistoryViewContract
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.BackButtonListener
import javax.inject.Inject

class SearchHistoryFragment : Fragment(), SearchHistoryViewContract, BackButtonListener {
    private var _view: FragmentSearchHistoryBinding? = null
    private val view get() = _view!!

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    var adapter: HistoryRVAdapter? = null

    @Inject
    lateinit var presenter: SearchHistoryPresenter

    companion object {
        @JvmStatic
        fun newInstance() = SearchHistoryFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
        presenter.attach(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _view = FragmentSearchHistoryBinding.inflate(inflater, container, false)
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadSearchHistory()
    }

    override fun initHistoryList(history: List<String>) {
        view.searchHistoryRv.layoutManager = LinearLayoutManager(context)
        adapter = HistoryRVAdapter(history) { word ->
            showMessage(word)
        }
        view.searchHistoryRv.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

    override fun onDestroy() {
        super.onDestroy()
        _view = null
        presenter.detach(this)
    }
}