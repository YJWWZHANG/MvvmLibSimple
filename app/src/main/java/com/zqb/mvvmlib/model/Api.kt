package com.zqb.mvvmlib.model

import com.zqb.mvvmlib.model.bean.ContactBean
import io.reactivex.Flowable
import retrofit2.http.POST

interface Api {

    @POST("/tsc/tscUser/getBookList")
    fun getContacts(): Flowable<ContactBean>

}
