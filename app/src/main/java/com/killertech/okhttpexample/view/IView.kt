package com.killertech.okhttpexample.view

import com.killertech.okhttpexample.model.Issues

interface IView {
    fun updateData(issues: Issues)
}