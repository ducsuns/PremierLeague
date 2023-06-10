package com.example.btl_mobile.Cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_mobile.Model.Cart
import com.example.btl_mobile.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_ticket.view.tvName
import kotlinx.android.synthetic.main.item_ticket_cart.view.*


class RecyclerViewCartAdapter (var listCart: List<Cart>):RecyclerView.Adapter<RecyclerViewCartAdapter.ViewHolder>() {
    lateinit var v:View
    private  var listener: OnDeleteItemClickListener?=null
    private  var mlistener: onIncrementQuantityClick?=null
    private var nlistener: onReduceQuantityClick?=null
    interface onReduceQuantityClick {
        fun onReduceQuantityClick(idCart: String)
    }
    fun setOnReduceQuantityClick(nlistener: onReduceQuantityClick) {
        this.nlistener = nlistener
    }
    interface OnDeleteItemClickListener {
        fun onDeleteItemClick(idCart: String)
    }
    fun setOnDeleteItemClickListener(listener: OnDeleteItemClickListener) {
        this.listener = listener
    }
    interface onIncrementQuantityClick {
        fun onIncrementQuantityClick(idCart: String)
    }
    fun setOnIncrementQuantityClick(mlistener: onIncrementQuantityClick) {
        this.mlistener = mlistener
    }


    class ViewHolder(v:View,listener: OnDeleteItemClickListener?,mlistener: onIncrementQuantityClick?,nlistener: onReduceQuantityClick?):RecyclerView.ViewHolder(v){
        var name=v.tvName
        var img1=v.img1
        var img2=v.img2
        var price=v.tvPrice
        var quantity=v.tvQuantity



        var btDelete=v.tvDelete
        var btAdd=v.btAdd
        var btSub=v.btSub
        lateinit var ticketCart:Cart
        fun bindView(ticketCart: Cart)
        {
            this.ticketCart = ticketCart
            Picasso.get().load(ticketCart.imgteam1Cart).into(img1)
            Picasso.get().load(ticketCart.imgteam2Cart).into(img2)
            name.text=ticketCart.nameCart
            price.text=ticketCart.priceCart
            quantity.text= ticketCart.quantity.toString()
        }
        init {
            btDelete.setOnClickListener {
                listener?.onDeleteItemClick(ticketCart.idCart!!)
            }
            btAdd.setOnClickListener {
                mlistener?.onIncrementQuantityClick(ticketCart.idCart!!)
            }
            btSub.setOnClickListener {
                nlistener?.onReduceQuantityClick(ticketCart.idCart!!)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        v= LayoutInflater.from(parent.context).inflate(R.layout.item_ticket_cart,parent,false)
        return ViewHolder(v,listener,mlistener,nlistener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bindView(listCart[position])
    }

    override fun getItemCount(): Int {
       return listCart.size
    }

}