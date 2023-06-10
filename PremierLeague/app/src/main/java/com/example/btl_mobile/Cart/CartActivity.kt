package com.example.btl_mobile.Cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_mobile.DAO.CartDAO
import com.example.btl_mobile.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_ticket_detail.btBack

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        var recyclerView = findViewById<RecyclerView>(R.id.rclvCart)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        var total=findViewById<TextView>(R.id.tvTotal)
        val dao=CartDAO()
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        val idUser = currentUser?.email

//        if (idUser != null)
            dao.getCarts(recyclerView,total)
        btBack.setOnClickListener {
            finish()
        }
        btCheckout.setOnClickListener {
            Toast.makeText(this,"Order Success",Toast.LENGTH_LONG).show()
        }

    }

}