package com.example.btl_mobile.Fragment.Me

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_mobile.Cart.CartActivity
import com.example.btl_mobile.DAO.CartDAO
import com.example.btl_mobile.DAO.TicketDAO
import com.example.btl_mobile.R
import com.example.btl_mobile.User.LoginActivity
import com.google.firebase.auth.FirebaseAuth


class MeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =inflater.inflate(R.layout.fragment_me, container, false)
        var recyclerView = view.findViewById<RecyclerView>(R.id.rclvTicket)
        recyclerView.layoutManager= LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)

        val dao = TicketDAO()
        dao.getTickets(recyclerView,requireContext())

        var bt=view.findViewById<ImageButton>(R.id.imgBtCart)
        var logout=view.findViewById<ImageButton>(R.id.imgBtLogout)
        var username=view.findViewById<TextView>(R.id.tvUser)

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
           var email = currentUser.email
            if (email != null) {
                username.text=email.split("@")[0]
            }
        }

        bt.setOnClickListener {
            var intent=Intent(this.context,CartActivity::class.java)
            startActivity(intent)
        }
       logout.setOnClickListener {
            var intent=Intent(this.context,LoginActivity::class.java)
            startActivity(intent)
        }


        var tvBadge=view.findViewById<TextView>(R.id.tvBadge)
        val cart =CartDAO()
        cart.getSizeCarts(tvBadge)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}





