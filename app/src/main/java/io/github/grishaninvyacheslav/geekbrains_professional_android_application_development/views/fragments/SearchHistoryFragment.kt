package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.databinding.FragmentSearchHistoryBinding
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_history.HistoryRVAdapter
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_history.HistoryScreenState
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_history.SearchHistoryViewModel
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_history.SearchHistoryViewModelFactory
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.BackButtonListener
import javax.inject.Inject

class SearchHistoryFragment : Fragment(), BackButtonListener {
    private var _view: FragmentSearchHistoryBinding? = null
    private val view get() = _view!!

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    var adapter: HistoryRVAdapter? = null

    @Inject
    lateinit var viewModelFactory: SearchHistoryViewModelFactory

    val viewModel: SearchHistoryViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(SearchHistoryViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchHistoryFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
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
        viewModel.getLiveHistory().observe(viewLifecycleOwner){
            when(it){
                is HistoryScreenState.DisplayingHistory -> initHistoryList(it.history)
                is HistoryScreenState.Loading -> Toast.makeText(requireContext(), "Загрузка", Toast.LENGTH_SHORT).show()
                is HistoryScreenState.Error -> Toast.makeText(requireContext(), "Произошла ошибка: $it", Toast.LENGTH_LONG).show()
            }
        }
        viewModel.loadSearchHistory()
    }

    private fun initHistoryList(history: List<String>) {
        view.searchHistoryRv.layoutManager = LinearLayoutManager(context)
        adapter = HistoryRVAdapter(history) { word ->
            showMessage(word)
        }
        view.searchHistoryRv.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = viewModel.backPressed()

    override fun onDestroy() {
        super.onDestroy()
        _view = null
    }
}