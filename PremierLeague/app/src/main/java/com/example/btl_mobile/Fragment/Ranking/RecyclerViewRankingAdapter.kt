 package com.example.btl_mobile.Fragment.Ranking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_mobile.Model.Team
import com.example.btl_mobile.R
import com.squareup.picasso.Picasso

class RecyclerViewRankingAdapter(var listTeam:List<Team>):RecyclerView.Adapter<RecyclerViewRankingAdapter.ViewHolder>() {
    lateinit var v:View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        v= LayoutInflater.from(parent.context).inflate(R.layout.item_ranking,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(listTeam[position])
    }

    override fun getItemCount(): Int {
        return listTeam.size
    }

    class ViewHolder(v: View) :RecyclerView.ViewHolder(v)
    {
        var imgTeam = v.findViewById<ImageView>(R.id.imgTeam)
        var teamName = v.findViewById<TextView>(R.id.nameTeam)
        var rank = v.findViewById<TextView>(R.id.rankTeam)
        var won = v.findViewById<TextView>(R.id.wonMatch)
        var lost = v.findViewById<TextView>(R.id.lostMatch)
        var point = v.findViewById<TextView>(R.id.point)
        var drawn = v.findViewById<TextView>(R.id.drawnMatch)
        fun bindView(team1: Team)
        {
            teamName.text =team1.name
            rank.text=team1.rank.toString()
            won.text = team1.won
            lost.text=team1.lost
            point.text=team1.point
            drawn.text= team1.drawn
            Picasso.get().load(team1.flagUrl).into(imgTeam)
        }

        init {
            v

        }
    }

}