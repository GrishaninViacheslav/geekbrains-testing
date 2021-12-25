package io.github.grishaninvyacheslav.geekbrains_professional_android_application_development.viewmodels

import androidx.annotation.CallSuper

abstract class MvpPresenter<T : ViewContract> : PresenterContract<T> {
    private var isFirstAttach = true

    private val attachedViews = arrayListOf<T>()

    protected fun forEachView(action: (view: T) -> Unit) {
        for (attachedView in attachedViews) {
            action(attachedView)
        }
    }

    override fun attach(view: T) {
        if (isFirstAttach) {
            isFirstAttach = false
            onFirstViewAttach()
        }
        attachedViews.add(view)
    }

    override fun detach(view: T) {
        attachedViews.remove(view)
        if (attachedViews.isEmpty()) {
            onDestroy()
        }
    }

    override fun getAttachedViews() = attachedViews

    protected open fun onFirstViewAttach() {}

    @CallSuper
    protected open fun onDestroy() {
        isFirstAttach = true
    }
}

