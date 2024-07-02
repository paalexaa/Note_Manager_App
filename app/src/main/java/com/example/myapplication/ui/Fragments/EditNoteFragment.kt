package com.example.myapplication.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.myapplication.Model.Notes
import com.example.myapplication.R
import com.example.myapplication.ViewModel.NotesViewModel
import com.example.myapplication.databinding.FragmentEditNoteBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Date

class EditNoteFragment : Fragment() {

    val oldNotes by navArgs<EditNoteFragmentArgs>()
    lateinit var binding:FragmentEditNoteBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)

        binding.EditTitle.setText(oldNotes.data.title)
        binding.EditSubtitle.setText(oldNotes.data.subTitle)
        binding.EditNotes.setText(oldNotes.data.notes)

        when(oldNotes.data.priority) {
            "1" -> {
                priority = "1"
                binding.pYellow.setImageResource(R.drawable.baseline_done_24)
                binding.pGreen.setImageResource(0)
                binding.pBlue.setImageResource(0)
                binding.pPink.setImageResource(0)
                binding.pPeach.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.pGreen.setImageResource(R.drawable.baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pBlue.setImageResource(0)
                binding.pPink.setImageResource(0)
                binding.pPeach.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.pBlue.setImageResource(R.drawable.baseline_done_24)
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)
                binding.pPink.setImageResource(0)
                binding.pPeach.setImageResource(0)
            }
            "4" -> {
                priority = "4"
                binding.pPink.setImageResource(R.drawable.baseline_done_24)
                binding.pGreen.setImageResource(0)
                binding.pBlue.setImageResource(0)
                binding.pYellow.setImageResource(0)
                binding.pPeach.setImageResource(0)
            }
            "5" -> {
                priority = "5"
                binding.pPeach.setImageResource(R.drawable.baseline_done_24)
                binding.pGreen.setImageResource(0)
                binding.pBlue.setImageResource(0)
                binding.pPink.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
        }

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

        binding.btnEditSaveNotes.setOnClickListener {
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.EditTitle.text.toString()
        val subTitle = binding.EditSubtitle.text.toString()
        val notes = binding.EditNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)

        val data = Notes(oldNotes.data.id,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )



        viewModel.updateNotes(data)

        Toast.makeText(requireContext(), "Notes Updated Successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete) {
            val bottomSheet:BottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialo_yes)
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialo_no)

            textViewYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id!!)
                bottomSheet.dismiss()

                Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)
            }

            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }

}