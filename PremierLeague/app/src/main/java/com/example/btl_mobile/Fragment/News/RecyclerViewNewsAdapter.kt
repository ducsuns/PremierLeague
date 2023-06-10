package com.example.btl_mobile.Fragment.News

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_mobile.Model.News
import com.example.btl_mobile.R
import com.squareup.picasso.Picasso

class RecyclerViewNewsAdapter(var listNews: ArrayList<News>, var transport: (link: String) -> Unit):RecyclerView.Adapter<RecyclerViewNewsAdapter.ViewHolder>() {

    lateinit var v:View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int    ): ViewHolder {
        v= LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(listNews[position])
        v.setOnClickListener {
            transport("https://www.premierleague.com/"+listNews[position].link)
        }
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)
    {
        var image = v.findViewById<ImageView>(R.id.imv_img)
        var content = v.findViewById<TextView>(R.id.tv_content)

        fun bindView(news : News)
        {
            content.text = news.content
            Picasso.get().load(news.imgUrl).into(image)
        }
    }

}