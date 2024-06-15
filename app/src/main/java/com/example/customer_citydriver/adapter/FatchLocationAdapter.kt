package com.example.customer_citydriver.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customer_citydriver.databinding.SearchItemViewBinding

class FatchLocationAdapter(var list: ArrayList<String>) : RecyclerView.Adapter<FatchLocationAdapter.LocationViewHolder>(){

    class LocationViewHolder( var binding: SearchItemViewBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LocationViewHolder {
        val binding = SearchItemViewBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return LocationViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val item = list[position]

        holder.binding.apply {

//            tvHeadLocation.text = item.location
//            imageLocation.setImageResource(item.image)
//            tvAddress.text = item.address
        }

    }

}