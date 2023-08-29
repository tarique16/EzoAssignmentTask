package com.example.ezoassignmenttask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ezoassignmenttask.R
import com.example.ezoassignmenttask.databinding.RecycleItemTaskOneBinding
import com.example.ezoassignmenttask.models.MetaData

class TaskOneRecyclerAdapter(private val model: List<MetaData>) :
    RecyclerView.Adapter<TaskOneRecyclerAdapter.ApiListData>() {

    inner class ApiListData(private val binding: RecycleItemTaskOneBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(item: MetaData) {
            Glide.with(binding.ivImage.context)
                .load(item.url)
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_error)
                .into(binding.ivImage)

            binding.apply {
                tvItemName.text = item.itemName
                tvItemPrice.text = "₹ ${item.itemPrice}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiListData {
        return ApiListData(
            RecycleItemTaskOneBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return model.size
    }

    override fun onBindViewHolder(holder: ApiListData, position: Int) {
        val currentItem = model[position]
        holder.bindItems(currentItem)
    }

}