package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.R
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.databinding.ActivityMainBinding
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.main.MainPresenter
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.presenters.main.MainViewContract
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.App
import io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.views.BackButtonListener
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainViewContract, BackButtonListener {
    private var _view: ActivityMainBinding? = null
    private val view get() = _view!!

    @Inject
    lateinit var presenter: MainPresenter

    private val navigator = AppNavigator(this, R.id.container)

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
        presenter.attach(this)
        super.onCreate(savedInstanceState)
        _view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    fun onLocalDestroy() {
        _view = null
        presenter.detach(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        onLocalDestroy()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
    }

    override fun backPressed() = presenter.backPressed()
}