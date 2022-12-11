package com.developer.chatapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.chatapp.databinding.ItemLayoutBinding


class RvAdapter (var list: List<String>) : RecyclerView.Adapter<RvAdapter.VP_Vh>() {

    inner class VP_Vh(var itemListBinding: ItemLayoutBinding):
        RecyclerView.ViewHolder(itemListBinding.root) {
        fun onBind(message:String) {

         itemListBinding.tvName.text=message
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VP_Vh {
        return VP_Vh(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    }

    override fun onBindViewHolder(holder: VP_Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size



}