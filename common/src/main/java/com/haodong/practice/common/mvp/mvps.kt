package com.haodong.practice.common.mvp

/**
 * created by linghaoDo on 2020/6/24
 * description:
 *
 * version:
 */
interface IPresenter<out View : IMvpView<IPresenter<View>>> : ILifecycle {
    val view: View
}


interface IMvpView<out Presenter : IPresenter<IMvpView<Presenter>>> : ILifecycle {
    val presenter: Presenter
}
