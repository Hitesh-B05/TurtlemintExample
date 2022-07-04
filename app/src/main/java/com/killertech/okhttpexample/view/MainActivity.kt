package com.killertech.okhttpexample.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.killertech.okhttpexample.databinding.ActivityMainBinding
import com.killertech.okhttpexample.model.Issues
import com.killertech.okhttpexample.presenter.IPresenter
import com.killertech.okhttpexample.presenter.MainActivityPresenter


class MainActivity : AppCompatActivity(), IView {
    /**
     * Binding instance.
     */
    private lateinit var mBinding : ActivityMainBinding
    /**
     * Presenter instance.
     */
    private lateinit var mPresenter : IPresenter
    private lateinit var issuesAdapter : IssuesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onResume() {
        super.onResume()
        initResources()
    }

    /**
     * Initialize all resources.
     */
    private fun initResources() {
        mPresenter = MainActivityPresenter(this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        mBinding.listViewIssues.layoutManager = layoutManager
    }

    /**
     * Fetch issue details.
     */
    fun fetchDetails(view: View) {
        mPresenter.getIssuesData()
    }

    override fun updateData(issues: Issues) {
        runOnUiThread { mBinding.listViewIssues.adapter = IssuesListAdapter(issues) }

    }
}