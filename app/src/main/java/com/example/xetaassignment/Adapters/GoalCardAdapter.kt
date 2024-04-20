package com.example.xetaassignment.Adapters

import Section1
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xetaassignment.R

data class GoalCardAdapter(val Carddata: Section1):
        RecyclerView.Adapter<GoalCardAdapter.ViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.goal_card_design, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // set the title
        holder.Title.text = Carddata.plan_name
        // Fetching the Progress
        val progress = Carddata.progress
        // Now removing the % so that we can convert the progress to float
        val a = progress.removeRange(2,3)
        val b = a.toFloat()
        // Now updating the progress
       holder.Progress.apply {
                                  progressMax = 100f
                                  b?.let { setProgressWithAnimation(it,1000) }
                                  progressBarWidth = 5f
                                  backgroundProgressBarWidth = 7f
                                  progressBarColor = Color.rgb(248, 181, 70)
                              }
        // updating the prograss text
        holder.ProgressText.text = Carddata.progress
    }

    class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {
        val Title : TextView
        val Progress : com.mikhaellopez.circularprogressbar.CircularProgressBar
        val ProgressText : TextView
        init {
            Title = itemview.findViewById(R.id.Goal_Card_title)
            Progress = itemview.findViewById(R.id.circularProgressBar)
            ProgressText = itemview.findViewById(R.id.Pb_tv)
        }
    }
}
