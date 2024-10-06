package com.cc17.progrifit30.fragments.list

import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cc17.progrifit30.R
import com.cc17.progrifit30.db.model.UserRoutes

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userRoutesList = emptyList<UserRoutes>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return userRoutesList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userRoutesList[position]
        holder.itemView.findViewById<TextView>(R.id.routeNameLabel).text = currentItem.name

        holder.itemView.findViewById<ConstraintLayout>(R.id.rowLayout).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(userRoutes: List<UserRoutes>) {
        this.userRoutesList = userRoutes
        notifyDataSetChanged()
    }
}