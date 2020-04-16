package com.zqb.mvvmlib.model

import com.zqb.mvvmlib.model.bean.ContactBean


data class ContactViewState(
    val isLoading: Boolean,
    val contactBean: ContactBean?
) {

    companion object {
        fun initial(): ContactViewState {
            return ContactViewState(isLoading = false, contactBean = null)
        }
    }
}
