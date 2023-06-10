package com.example.btl_mobile.DAO

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_mobile.Fragment.Me.RecyclerViewMeAdapter
import com.example.btl_mobile.Fragment.Me.TicketDetail
import com.example.btl_mobile.Model.Ticket
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TicketDAO {
    fun getTickets(recyclerView: RecyclerView,context: Context) {
        val ds = arrayListOf<Ticket>()
        val dbRef = FirebaseDatabase.getInstance().getReference("btl_mobile")
        val dbRef1 = dbRef.child("Ticket")

        dbRef1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                ds.clear()
                if(snapshot.exists()){
                    for(ticketSnap in snapshot.children){
                        val ticketData=ticketSnap.getValue(Ticket::class.java)
                        ds.add(ticketData!!)
                    }
                    val mAdapter = RecyclerViewMeAdapter(ds)
                    recyclerView.adapter = mAdapter
                    mAdapter.setOnItemClickListener(object : RecyclerViewMeAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(context, TicketDetail::class.java)
                            intent.putExtra("id",ds[position].id)
                            intent.putExtra("name",ds[position].name)
                            intent.putExtra("date",ds[position].date)
                            intent.putExtra("stadium",ds[position].stadium)
                            intent.putExtra("imgteam1",ds[position].imgteam1)
                            intent.putExtra("imgteam2",ds[position].imgteam2)
                            intent.putExtra("price",ds[position].price)
                            intent.putExtra("time",ds[position].time)
                            context.startActivity(intent)
                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error.
            }
        })
    }
}
