package com.example.ezoassignmenttask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ezoassignmenttask.databinding.RecycleItemTaskOneBinding

data class CoordinatorListModel(
    val id: Int,
    val name: String,
    val age: Int
)

class CoordinatorLayoutRecyclerAdapter :
    ListAdapter<CoordinatorListModel, CoordinatorLayoutRecyclerAdapter.MainClass>(object :
        DiffUtil.ItemCallback<CoordinatorListModel>() {
        override fun areItemsTheSame(
            oldItem: CoordinatorListModel,
            newItem: CoordinatorListModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CoordinatorListModel,
            newItem: CoordinatorListModel
        ): Boolean {
            return oldItem == newItem
        }
    }) {
    inner class MainClass(private val binding: RecycleItemTaskOneBinding) :
        ViewHolder(binding.root) {
        fun bind(model: CoordinatorListModel) {
            binding.apply {
                val params = ivImage.layoutParams
                params.height = 0
                ivImage.layoutParams = params
                tvItemName.text = model.name
                tvItemPrice.text = model.age.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainClass {
        return MainClass(
            RecycleItemTaskOneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainClass, position: Int) {
        holder.bind(getItem(position))
    }
}