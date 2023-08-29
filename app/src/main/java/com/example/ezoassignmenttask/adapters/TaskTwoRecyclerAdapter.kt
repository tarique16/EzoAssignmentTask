package com.example.ezoassignmenttask.adapters

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ezoassignmenttask.R
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

        private fun setColor(int: Int): Int {
            return ContextCompat.getColor(binding.tvLog.context, int)
        }

        fun bindItems(item: Logs) {
            binding.apply {
                val colorGreen = setColor(R.color.log_green_text)
                val colorBlue = setColor(R.color.log_blue_text)
                val colorRed = setColor(R.color.log_red_text)
                val background = root.background as GradientDrawable
                if (item.message.contains("Withdraw")) {
                    tvLog.setTextColor(colorGreen)

                    background.let {
                        it.setStroke(2/*.dpToPx(tvLog.resources)*/, colorGreen)
                        it.setColor(setColor(R.color.log_green))
                    }
                } else if (item.message.contains("Deposit")) {
                    tvLog.setTextColor(colorBlue)
                    background.let {
                        it.setStroke(2/*.dpToPx(tvLog.resources)*/, colorBlue)
                        it.setColor(setColor(R.color.log_blue))
                    }
                } else {
                    tvLog.setTextColor(colorRed)
                    background.let {
                        it.setStroke(2/*.dpToPx(tvLog.resources)*/, colorRed)
                        it.setColor(setColor(R.color.log_red))
                    }
                }
                binding.tvLog.text = item.message
            }
        }

    }

//    fun Int.dpToPx(res:Resources): Int {
//        val scale = res.displayMetrics.density
//        return (this * scale + 0.5f).toInt()
//    }

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