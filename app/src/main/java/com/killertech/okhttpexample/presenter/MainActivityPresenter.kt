package com.killertech.okhttpexample.presenter

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.killertech.okhttpexample.model.Issue
import com.killertech.okhttpexample.model.Issues
import com.killertech.okhttpexample.model.NetworkApi
import com.killertech.okhttpexample.model.User
import com.killertech.okhttpexample.view.MainActivity
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class MainActivityPresenter(mainView: MainActivity) : IPresenter {

    var networkApi : NetworkApi
    var mResponseJson : String? = null
    var mView : MainActivity

    init {
        networkApi = NetworkApi(this)
        mView = mainView
    }
    override fun getIssuesData() {
        networkApi.fetchData()
        //parseJsonData("")
    }

    override fun parseJsonData(jsonResponseString: String) {
        mResponseJson = jsonResponseString
        val jsonArray :JSONArray = JSONArray(mResponseJson)
        val issueList: ArrayList<Issue> = ArrayList()
        for (index in 0..(jsonArray.length() - 1)) {
            val issueObject: JSONObject = jsonArray.get(index) as JSONObject
            val userObject: JSONObject = issueObject.getJSONObject("user")
            val user: User = User(userObject.getString("login"),
                userObject.getString("avatar_url"))
            val issue: Issue = Issue(issueObject.getString("title"),
                issueObject.getString("body"), user, issueObject.getString("updated_at"),
                issueObject.getString("comments_url"))
            issueList.add(issue)
        }
        val issues: Issues = Issues(issueList)
        mView.updateData(issues)
    }
}