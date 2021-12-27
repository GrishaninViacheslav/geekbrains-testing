package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.databinding.FragmentSearchInputBinding
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_input.InputScreenState
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels.search_input.SearchInputViewModel
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.BackButtonListener

class SearchInputFragment : Fragment(), BackButtonListener {
    private var _view: FragmentSearchInputBinding? = null
    private val view get() = _view!!

    val viewModel: SearchInputViewModel by viewModels()

    private fun setViewListeners() {
        with(view) {
            searchInput.setOnKeyListener { v, keyCode, event ->
                if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    viewModel.submitQuery(searchInput.text.toString())
                    return@setOnKeyListener true
                }
                view.errorMessage.text = ""
                return@setOnKeyListener false
            }
            searchConfirm.setOnClickListener {
                viewModel.submitQuery(searchInput.text.toString())
            }
            toHistory.setOnClickListener {
                viewModel.openHistory()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchInputFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _view = FragmentSearchInputBinding.inflate(inflater, container, false)
        return view.apply {
            setViewListeners()
            viewModel.getLiveValidation().observe(viewLifecycleOwner){
                if(it is InputScreenState.Error){
                    showMessage(it.error.message ?: "Произошла ошибка")
                }
            }
        }.root
    }

    private fun showMessage(message: String) {
        view.errorMessage.text = message
        Log.d("[SearchInputFragment]", "view.errorMessage.text: ${view.errorMessage.text}")
        Toast.makeText(
            context,
            "view.errorMessage.text: ${view.errorMessage.text}",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _view = null
    }

    override fun backPressed() = viewModel.backPressed()
}