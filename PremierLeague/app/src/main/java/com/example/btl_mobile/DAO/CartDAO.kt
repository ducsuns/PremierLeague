package com.example.btl_mobile.DAO


import android.content.Context
import android.provider.ContactsContract
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_mobile.Cart.CartActivity
import com.example.btl_mobile.Cart.RecyclerViewCartAdapter
import com.example.btl_mobile.Model.Cart
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartDAO {
    fun getCarts(recyclerView: RecyclerView,tvTotal: TextView) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val idUser = currentUser?.email
        val ds= arrayListOf<Cart>()
        val dbRef= FirebaseDatabase.getInstance().getReference("btl_mobile").child("Cart")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                ds.clear()
                if(snapshot.exists()){
                    for (cartSnapshot in snapshot.children){
                        val cartData=cartSnapshot.getValue(Cart::class.java)
//                        if (cartData != null) {
//                            if(cartData.equals(idUser))
//
//                        }
                        ds.add(cartData!!)
                    }
                    var mAdapter = RecyclerViewCartAdapter(ds)

                    mAdapter.setOnDeleteItemClickListener(object : RecyclerViewCartAdapter.OnDeleteItemClickListener {
                        override fun onDeleteItemClick(idCart: String) {
                            // Xóa sản phẩm khỏi Cart
                            dbRef.child(idCart).removeValue()
//                            Toast.makeText(CartActivity,"Đã xóa",Toast.LENGTH_SHORT).show()
                        }
                    })
                    mAdapter.setOnIncrementQuantityClick(object : RecyclerViewCartAdapter.onIncrementQuantityClick {
                        override fun onIncrementQuantityClick(idCart: String) {
                            // Tăng số lượng sản phẩm và cập nhật Firebase
                            incrementQuantity(idCart)
                        }
                    })
                    mAdapter.setOnReduceQuantityClick(object : RecyclerViewCartAdapter.onReduceQuantityClick {
                        override fun onReduceQuantityClick(idCart: String) {
                            //Giảm số lượng sản phẩm và cập nhật Firebase
                            reduceQuantity(idCart)
                        }
                    })

                    recyclerView.adapter=mAdapter

                    val totalPrice = calculateTotalPrice(ds)
                    tvTotal.text= totalPrice.toString()

                }
            }
            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    // Phương thức tính tổng giá trị cart
    fun calculateTotalPrice(ds: List<Cart>): Double {
        var totalPrice = 0.0
        for (cart in ds) {
            val quantity = cart.quantity ?: 0
            val price = cart.priceCart?.toDouble()?:0.0
            totalPrice += quantity * price
        }
        return totalPrice
    }

    private fun incrementQuantity(idCart: String) {
        val cartRef = FirebaseDatabase.getInstance().getReference("btl_mobile/Cart/$idCart")
        cartRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val cartData = snapshot.getValue(Cart::class.java)
                cartData?.let {
                    it.quantity = it.quantity?.plus(1)
                    cartRef.setValue(it)
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
    private fun reduceQuantity(idCart: String) {
        val cartRef = FirebaseDatabase.getInstance().getReference("btl_mobile/Cart/$idCart")
        cartRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val cartData = snapshot.getValue(Cart::class.java)
                cartData?.let {
                    it.quantity = it.quantity?.minus(1)
                    cartRef.setValue(it)
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
    fun getSizeCarts(tvBadge: TextView) {
        val ds= arrayListOf<Cart>()
        val dbRef= FirebaseDatabase.getInstance().getReference("btl_mobile").child("Cart")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                ds.clear()
                if(snapshot.exists()){
                    for (cartSnapshot in snapshot.children){
                        val cartData=cartSnapshot.getValue(Cart::class.java)
                        ds.add(cartData!!)
                    }
                    tvBadge.text=ds.size.toString()
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}

