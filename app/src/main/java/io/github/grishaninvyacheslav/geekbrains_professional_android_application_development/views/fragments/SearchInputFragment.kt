package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.databinding.FragmentSearchInputBinding
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_input.SearchInputPresenter
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.search_input.SearchInputView
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.BackButtonListener
import javax.inject.Inject

class SearchInputFragment : Fragment(), SearchInputView, BackButtonListener {
    private var _view: FragmentSearchInputBinding? = null
    private val view get() = _view!!

    @Inject
    lateinit var presenter: SearchInputPresenter

    private fun setViewListeners() {
        with(view) {
            searchInput.setOnKeyListener { v, keyCode, event ->
                if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    presenter.submitQuery(searchInput.text.toString())
                    return@setOnKeyListener true
                }
                view.errorMessage.text = ""
                return@setOnKeyListener false
            }
            searchConfirm.setOnClickListener {
                presenter.submitQuery(searchInput.text.toString())
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchInputFragment()
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
        _view = FragmentSearchInputBinding.inflate(inflater, container, false)
        return view.apply { setViewListeners() }.root
    }

    override fun showMessage(message: String) {
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
        presenter.detach(this)
    }

    override fun backPressed() = presenter.backPressed()
}