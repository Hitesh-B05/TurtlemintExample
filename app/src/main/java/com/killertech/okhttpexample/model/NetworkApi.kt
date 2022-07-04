package com.killertech.okhttpexample.model

import android.content.res.AssetManager
import android.util.Log
import com.killertech.okhttpexample.presenter.IPresenter
import okhttp3.*
import java.io.IOException

class NetworkApi(presenter : IPresenter) {
    private var mHttpClient : OkHttpClient
    private var mRequest : Request
    private val urlString : String = "https://api.github.com/repos/square/okhttp/issues"
    private lateinit var mResponseString : String
    private lateinit var mPresenter : IPresenter

    init {
        mHttpClient = OkHttpClient.Builder().build()
        mRequest = Request.Builder().url(urlString).build()
        mPresenter = presenter
    }

    fun fetchData() {
        mHttpClient.newCall(mRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("NetworkApi -> fetchData", "onFailure" + e.stackTrace)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    Log.d("NetworkApi -> fetchData", "onResponse successful")
                    val responseJsonString: String? = response.body?.string()
                    if (responseJsonString != null) {
                        updateJsonString(responseJsonString)
                    }
                }
            }
        })
    }

    fun updateJsonString(jsonResponseString : String) {
        mResponseString = jsonResponseString
        mPresenter.parseJsonData(mResponseString)
    }
}
