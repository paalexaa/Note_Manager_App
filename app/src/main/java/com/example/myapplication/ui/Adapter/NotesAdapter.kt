package com.example.myapplication.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.Notes
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemNotesBinding
import com.example.myapplication.ui.Fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, var notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    fun filtering(newFilterList: ArrayList<Notes>) {
        notesList = newFilterList
        notifyDataSetChanged()
    }

    class notesViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = notesList.size

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]
            holder.binding.notesTitle.text = data.title
            holder.binding.notesSubtitle.text = data.subTitle
            holder.binding.notesDate.text = data.date

            when(data.priority) {
                "1" -> {
                    holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
                }
                "2" -> {
                    holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
                }
                "3" -> {
                    holder.binding.viewPriority.setBackgroundResource(R.drawable.blue_dot)
                }
                "4" -> {
                    holder.binding.viewPriority.setBackgroundResource(R.drawable.pink_dot)
                }
                "5" -> {
                    holder.binding.viewPriority.setBackgroundResource(R.drawable.peach_dot)
                }
            }
        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }
}