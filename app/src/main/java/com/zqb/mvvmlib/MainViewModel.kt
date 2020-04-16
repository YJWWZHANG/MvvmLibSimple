package com.zqb.mvvmlib

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.LogUtils
import com.uber.autodispose.autoDisposable
import com.zqb.mvvm.base.viewmodel.BaseViewModel
import com.zqb.mvvm.ext.reactivex.copyMap
import com.zqb.mvvm.util.SingletonHolderSingleArg
import com.zqb.mvvmlib.model.Api
import com.zqb.mvvmlib.model.ContactViewState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class MainViewModel(var mApi: Api): BaseViewModel() {

    private val mViewStateSubject: BehaviorSubject<ContactViewState> =
        BehaviorSubject.createDefault(ContactViewState.initial())

    fun observeViewState(): Observable<ContactViewState> {
        return mViewStateSubject.hide().distinctUntilChanged()
    }

    @SuppressLint("AutoDispose", "CheckResult")
    fun getContacts() {
        mApi.getContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                mViewStateSubject.copyMap {
                    it.copy(isLoading = true, contactBean = null)
                }
            }
            .autoDisposable(this)
            .subscribe({ contactBean ->
                mViewStateSubject.copyMap {
                    it.copy(isLoading = false, contactBean = contactBean)
                }
            }, { th ->
                LogUtils.e(th.message)
                mViewStateSubject.copyMap {
                    it.copy(isLoading = false, contactBean = null)
                }
            })

    }

    @Suppress("UNCHECKED_CAST")
    class MainViewModelFactory(
        private val repo: Api
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MainViewModel(repo) as T

        companion object : SingletonHolderSingleArg<MainViewModelFactory, Api>(::MainViewModelFactory)

    }
}