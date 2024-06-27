package com.example.ezoassignmenttask.fragment

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ezoassignmenttask.adapters.ContactsRecyclerAdapter
import com.example.ezoassignmenttask.databinding.FragmentContactListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class ContactListFragment : Fragment() {

    private var _binding:FragmentContactListBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAdapter: ContactsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pbLoader.visibility = VISIBLE
        CoroutineScope(Dispatchers.Main).launch {
            mAdapter = ContactsRecyclerAdapter()
            binding.rvCoordinator.adapter = mAdapter
            val contacts = fetchContactsAsync(requireActivity())

            for (contact in contacts) {
                println("Name: ${contact.name}")
                for (phone in contact.phones) {
                    println("Phone: $phone")
                }
            }
            mAdapter.submitList(contacts)
            binding.pbLoader.visibility = GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private suspend fun fetchContactsAsync(context: Context): List<Contact> {
        return withContext(Dispatchers.IO) {
            val contacts = mutableListOf<Contact>()
            val contentResolver: ContentResolver = context.contentResolver
            val cursor: Cursor? = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null
            )

            cursor?.use {
                if (it.moveToFirst()) {
                    do {
                        val contactId =
                            it.getString(it.getColumnIndex(ContactsContract.Contacts._ID))
                        val name =
                            it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))

                        val phones = mutableListOf<String>()
                        val phoneCursor: Cursor? = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            arrayOf(contactId),
                            null
                        )

                        phoneCursor?.use { pcursor ->
                            while (pcursor.moveToNext()) {
                                val phoneNumber =
                                    pcursor.getString(pcursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                                phones.add(phoneNumber)
                            }
                        }

                        contacts.add(Contact(name, phones))

                    } while (it.moveToNext())
                }
            }

            contacts.sortedBy { it.name.lowercase(Locale.ROOT) }
        }
    }

    data class Contact(val name: String, val phones: List<String>)
}