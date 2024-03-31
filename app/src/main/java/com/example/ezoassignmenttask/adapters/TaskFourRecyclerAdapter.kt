package com.example.ezoassignmenttask.adapters

import android.media.AudioManager
import android.media.MediaPlayer
import android.media.ToneGenerator
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ezoassignmenttask.R
import com.example.ezoassignmenttask.databinding.RecycleItemTaskOneBinding
import com.example.ezoassignmenttask.databinding.RecyclerItemTaskFourImageBinding
import com.example.ezoassignmenttask.models.FourMetaData


class TaskFourRecyclerAdapter : ListAdapter<FourMetaData, ViewHolder>(object :
    DiffUtil.ItemCallback<FourMetaData>() {
    override fun areItemsTheSame(oldItem: FourMetaData, newItem: FourMetaData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FourMetaData, newItem: FourMetaData): Boolean {
        return oldItem == newItem
    }
}) {

    inner class ImageViewData(val binding: RecyclerItemTaskFourImageBinding) :
        ViewHolder(binding.root) {
        fun bindItems(model: FourMetaData) {
            binding.tvToneValue.text = model.itemPrice.toString()
            if (model.itemName == "Star") {
                binding.tvToneValue.setTextColor(
                    ResourcesCompat.getColor(
                        binding.tvToneValue.resources,
                        R.color.log_red_text, null
                    )
                )
            } else if (model.itemPrice!! > 100) {
                binding.tvToneValue.setTextColor(
                    ResourcesCompat.getColor(
                        binding.tvToneValue.resources,
                        R.color.black, null
                    )
                )
            }
            binding.tvToneValue.setOnClickListener {
                if (model.itemPrice!! >= 101 && model.itemBarcode != null) {
                    val mPlayer: MediaPlayer =
                        MediaPlayer.create(binding.root.context, model.itemBarcode)
                    if (mPlayer.isPlaying) {
                        mPlayer.stop()
                    }
                    mPlayer.start()
                } else {
                    val toneGenerator = ToneGenerator(AudioManager.STREAM_SYSTEM, 100)
                    model.itemBarcode?.let { toneGenerator.startTone(it, 300) }
                }
            }
            /*if (model.url?.contains(".mp4") == true) {
                binding.vvVideo.visibility = VISIBLE
                binding.ivImage.visibility = GONE
                val video =
                    Uri.parse("android.resource://" + binding.vvVideo.context.packageName + "/" + R.raw.test_video)
                val videoUrl = Uri.parse(model.url)
                binding.vvVideo.setVideoURI(videoUrl)
                binding.vvVideo.start()
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.vvVideo.pause()
                }, 5000)
            } else {
                binding.ivImage.visibility = VISIBLE
                binding.vvVideo.visibility = GONE
                Glide.with(binding.ivImage.context)
                    .load(model.url)
                    .placeholder(
                        ContextCompat.getDrawable(
                            binding.ivImage.context,
                            R.drawable.loading_animation
                        )
                    )
                    .transition(DrawableTransitionOptions.withCrossFade(1000))
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                    .into(binding.ivImage)
            }*/
        }
    }

    inner class OtherData(val binding: RecycleItemTaskOneBinding) : ViewHolder(binding.root) {
        fun bindItems(model: FourMetaData) {
            binding.apply {
                tvItemName.text = model.itemName
                tvItemPrice.text = model.itemPrice.toString()
                ivImage.setImageDrawable(model.drawable)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            0 -> {
                ImageViewData(
                    RecyclerItemTaskFourImageBinding.inflate(LayoutInflater.from(parent.context))
                )
            }

            1 -> {
                OtherData(
                    RecycleItemTaskOneBinding.inflate(LayoutInflater.from(parent.context))
                )
            }

            else -> {
                OtherData(
                    RecycleItemTaskOneBinding.inflate(LayoutInflater.from(parent.context))
                )
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ImageViewData -> holder.bindItems(getItem(position))
            is OtherData -> holder.bindItems(getItem(position))
        }
    }
}