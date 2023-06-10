package com.example.btl_mobile.Fragment.Team

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_mobile.Model.Team
import com.example.btl_mobile.R
import com.squareup.picasso.Picasso

class RecyclerViewTeamAdapter(var listTeam: ArrayList<Team>):RecyclerView.Adapter<RecyclerViewTeamAdapter.ViewHolder>() {

    lateinit var v:View
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        v= LayoutInflater.from(parent.context).inflate(R.layout.item_team,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(listTeam[position])
    }

    override fun getItemCount(): Int {
        return listTeam.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        var imgView = v.findViewById<ImageView>(R.id.imgTeam)
        var teamName = v.findViewById<TextView>(R.id.nameTeam)
        fun bindView(team1: Team)
        {
            teamName.text =team1.name
            Picasso.get().load(team1.flagUrl).into(imgView)
        }

        init {
            v
        }
    }
}