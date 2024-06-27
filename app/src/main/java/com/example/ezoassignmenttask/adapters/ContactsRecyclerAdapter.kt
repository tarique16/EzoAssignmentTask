package com.example.ezoassignmenttask.adapters

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ezoassignmenttask.databinding.RecycleItemTaskOneBinding
import com.example.ezoassignmenttask.fragment.ContactListFragment.Contact

class ContactsRecyclerAdapter : ListAdapter<Contact, ContactsRecyclerAdapter.ContactClass>(object :
    DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(
        oldItem: Contact,
        newItem: Contact
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Contact,
        newItem: Contact
    ): Boolean {
        return oldItem == newItem
    }

}) {
    inner class ContactClass(private val binding: RecycleItemTaskOneBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(model: Contact) {
            binding.apply {
                ivImage.visibility = GONE
                tvItemName.text = model.name
                tvItemPrice.text = model.phones.joinToString(separator = "\n","").trim()
                tvItemPrice.gravity = Gravity.RIGHT
                tvItemName.gravity = Gravity.RIGHT
//                tvItemPrice.text = model.phones[0]
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactClass {
        return ContactClass(
            RecycleItemTaskOneBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactClass, position: Int) {
        holder.bindItems(getItem(position))
    }
}