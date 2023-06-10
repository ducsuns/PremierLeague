package com.example.btl_mobile.Fragment.Ranking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_mobile.DAO.Controller
import com.example.btl_mobile.R


class RankingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ranking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var  recyclerView :RecyclerView = view.findViewById(R.id.rclvRanking)
        var  recyclerAdapter = RecyclerViewRankingAdapter(Controller.getListTeamSorted())
        recyclerView.adapter=  recyclerAdapter
        recyclerView.layoutManager=  LinearLayoutManager(this.context)

    }

}