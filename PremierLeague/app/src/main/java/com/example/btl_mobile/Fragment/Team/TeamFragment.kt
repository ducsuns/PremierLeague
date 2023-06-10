package com.example.btl_mobile.Fragment.Team

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_mobile.DAO.Controller
import com.example.btl_mobile.R


class TeamFragment : Fragment() {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_team, container, false)
        var recyclerView:RecyclerView = view.findViewById(R.id.rclvTeam)
        var  recyclerViewAdapter = RecyclerViewTeamAdapter(Controller.listTeam)

        recyclerView.adapter=recyclerViewAdapter
        recyclerView.layoutManager = GridLayoutManager(this.context,4)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}