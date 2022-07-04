package com.killertech.okhttpexample.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.killertech.okhttpexample.R
import com.killertech.okhttpexample.model.Issue
import com.killertech.okhttpexample.model.Issues
import java.text.SimpleDateFormat
import java.util.*

class IssuesListAdapter(private val issues: Issues) : RecyclerView.Adapter<IssuesListAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.issues_list_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val issue: Issue = issues.issues[position]
        holder.issue.text = issue.title
        if (issue.body.length < 200) {
            holder.description.text = issue.body
        } else {
            holder.description.text = issue.body.subSequence(0, 200)
        }
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(issue.updated_at)
        holder.dateUpdated.text = SimpleDateFormat("MM-dd-yyyy").format(date)
        holder.userName.text = issue.user.login
    }

    override fun getItemCount(): Int {
        return issues.issues.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val issue : TextView = itemView.findViewById(R.id.tvIssue)
        val description : TextView = itemView.findViewById(R.id.tvIssueDescription)
        val dateUpdated : TextView = itemView.findViewById(R.id.tvDateUpdated)
        val userName : TextView = itemView.findViewById(R.id.tvUserName)
        //val userAvatar : ImageView = itemView.findViewById(R.id.ivUserAvatar)
    }

}

