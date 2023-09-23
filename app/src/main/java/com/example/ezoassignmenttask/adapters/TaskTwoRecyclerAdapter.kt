package com.example.ezoassignmenttask.adapters

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ezoassignmenttask.R
import com.example.ezoassignmenttask.Utility.Companion.setColor
import com.example.ezoassignmenttask.databinding.RecyclerItemTaskTwoLogsBinding
import com.example.ezoassignmenttask.models.Logs

class TaskTwoRecyclerAdapter : ListAdapter<Logs, TaskTwoRecyclerAdapter.LogsData>(
    object : DiffUtil.ItemCallback<Logs>() {
        override fun areItemsTheSame(oldItem: Logs, newItem: Logs): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Logs, newItem: Logs): Boolean {
            return oldItem.message == newItem.message
        }
    }
) {

    inner class LogsData(private val binding: RecyclerItemTaskTwoLogsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: Logs) {
            binding.apply {
                val mainBackground =
                    ResourcesCompat.getDrawable(root.resources, R.drawable.rectangle_border, null)
                val background =
                    mainBackground?.constantState?.newDrawable()?.mutate() as GradientDrawable
                val textColorResId = when {
                    item.message.contains("Withdraw") -> R.color.log_green_text
                    item.message.contains("Deposit") -> R.color.log_blue_text
                    else -> R.color.log_red_text
                }
                val strokeColorResId = when {
                    item.message.contains("Withdraw") -> R.color.log_green
                    item.message.contains("Deposit") -> R.color.log_blue
                    else -> R.color.log_red
                }
                val textColor = setColor(tvLog.context, textColorResId)
                val strokeColor = setColor(tvLog.context, textColorResId)

                tvLog.setTextColor(textColor)
                tvLog.text = item.message
                background.setStroke(2, strokeColor)
                background.setColor(setColor(tvLog.context, strokeColorResId))
                root.background = background
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogsData {
        return LogsData(
            RecyclerItemTaskTwoLogsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LogsData, position: Int) {
        holder.bindItems(getItem(position))
    }
}