package com.killertech.okhttpexample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Issue(val title: String, val body : String, val user : User,
                 val updated_at : String, val comments_url : String)