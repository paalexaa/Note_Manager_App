package com.example.myapplication.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.myapplication.Model.Notes
import com.example.myapplication.R
import com.example.myapplication.ViewModel.NotesViewModel
import com.example.myapplication.databinding.FragmentCreateNoteBinding
import java.util.Date

class CreateNoteFragment : Fragment() {

    lateinit var binding: FragmentCreateNoteBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)

        binding.pYellow.setImageResource(R.drawable.baseline_done_24)


        binding.pYellow.setOnClickListener {
            priority = "1"
            binding.pYellow.setImageResource(R.drawable.baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pBlue.setImageResource(0)
            binding.pPink.setImageResource(0)
            binding.pPeach.setImageResource(0)
        }
        binding.pGreen.setOnClickListener {
            priority = "2"
            binding.pGreen.setImageResource(R.drawable.baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pBlue.setImageResource(0)
            binding.pPink.setImageResource(0)
            binding.pPeach.setImageResource(0)
        }
        binding.pBlue.setOnClickListener {
            priority = "3"
            binding.pBlue.setImageResource(R.drawable.baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.pPink.setImageResource(0)
            binding.pPeach.setImageResource(0)
        }
        binding.pPink.setOnClickListener {
            priority = "4"
            binding.pPink.setImageResource(R.drawable.baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pBlue.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.pPeach.setImageResource(0)
        }
        binding.pPeach.setOnClickListener {
            priority = "5"
            binding.pPeach.setImageResource(R.drawable.baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pBlue.setImageResource(0)
            binding.pPink.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.EditTitle.text.toString()
        val subTitle = binding.EditSubtitle.text.toString()
        val notes = binding.EditNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)

        val data = Notes(null,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority
            )
        viewModel.addNotes(data)

        Toast.makeText(requireContext(), "Notes Created Successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment)
    }

}