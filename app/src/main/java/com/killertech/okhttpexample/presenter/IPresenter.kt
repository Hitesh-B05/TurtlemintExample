package com.killertech.okhttpexample.presenter

import com.killertech.okhttpexample.model.Issue

interface IPresenter {
    fun getIssuesData()
    fun parseJsonData(jsonResponseString : String)
}