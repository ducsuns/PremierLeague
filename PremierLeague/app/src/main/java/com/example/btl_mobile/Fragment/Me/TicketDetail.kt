package com.example.btl_mobile.Fragment.Me

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.btl_mobile.Model.Cart
import com.example.btl_mobile.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_ticket_detail.*

class TicketDetail : AppCompatActivity() {
    private lateinit var dbRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_detail)
        init()
        btBack.setOnClickListener {
            finish()
        }
        btAddtocart.setOnClickListener {
               AddToCart()
        }
    }

    private fun AddToCart() {
        dbRef = FirebaseDatabase.getInstance().getReference("btl_mobile").child("Cart").child(intent.getStringExtra("id").toString())

        //lấy dữ liệu ticket
        val nameCart=intent.getStringExtra("name")
        val priceCart=intent.getStringExtra("price")
        val imgteam1Cart=intent.getStringExtra("imgteam1")
        val imgteam2Cart=intent.getStringExtra("imgteam2")
        val quantity=1

//        val currentUser = FirebaseAuth.getInstance().currentUser
//        val idUser = currentUser?.email

        //dẩy dữ liệu vào
        val idCart=dbRef.key!!
        val cart= Cart(idCart,nameCart,priceCart,imgteam1Cart,imgteam2Cart,quantity)
        dbRef.setValue(cart)
            .addOnCompleteListener {
                Toast.makeText(this,"Đã thêm vào giỏ hàng",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
            }


    }

    private fun init() {
        val s=intent.getStringExtra("date")
        tvTime.text=intent.getStringExtra("time")
        tvName.text=intent.getStringExtra("name")
//        tvMonth.text=intent.getStringExtra("month")
        if (s != null) {
            tvDate.text=s.split("/")[0]
        }
        if (s != null) {
            tvMonth.text=s.split("/")[1]
        }
        tvStadium.text=intent.getStringExtra("stadium")
        tvPrice.text=intent.getStringExtra("price")
        Picasso.get()
            .load(intent.getStringExtra("imgteam1"))
            .into(img1) // Truyền ImageView vào để hiển thị ảnh
        Picasso.get()
            .load(intent.getStringExtra("imgteam2"))
            .into(img2) // Truyền ImageView vào để hiển thị ảnh
    }

}