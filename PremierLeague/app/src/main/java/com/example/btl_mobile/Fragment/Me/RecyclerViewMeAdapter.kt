package com.example.btl_mobile.Fragment.Me

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_mobile.Model.Ticket
import com.example.btl_mobile.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_ticket.view.*

class RecyclerViewMeAdapter (var listTicket: List<Ticket>): RecyclerView.Adapter<RecyclerViewMeAdapter.ViewHolder>(){
    lateinit var v: View
    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }
    class ViewHolder (v: View,clickListener: onItemClickListener):RecyclerView.ViewHolder(v){
        var month=v.tvMonth
        var date=v.tvDate
        var image1=v.imageT1
        var image2=v.imageT2
        var doi=v.tvName
        var stadium=v.tvStadium

        fun bindView(ticket: Ticket)
        {
            var s=ticket.date
//            month.text=ticket.month
            if (s != null) {
                date.text =s.split("/") [0]
            }
            if (s != null) {
                month.text =s.split("/") [1]
            }
            Picasso.get().load(ticket.imgteam1).into(image1)
            Picasso.get().load(ticket.imgteam2).into(image2)
            doi.text=ticket.name
            stadium.text=ticket.stadium
         }
        init {
            v.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        v= LayoutInflater.from(parent.context).inflate(R.layout.item_ticket,parent,false)
        return RecyclerViewMeAdapter.ViewHolder(v,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         return holder.bindView(listTicket[position])

    }

    override fun getItemCount(): Int {
        return listTicket.size
    }
}