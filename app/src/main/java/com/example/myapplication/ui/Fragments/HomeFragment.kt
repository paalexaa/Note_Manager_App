package com.example.myapplication.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.Model.Notes
import com.example.myapplication.R
import com.example.myapplication.ViewModel.NotesViewModel
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.Adapter.NotesAdapter

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        // Set layout manager
        val gridLayoutManager = GridLayoutManager(context, 2)
        binding.rcvAllNotes.layoutManager = gridLayoutManager

        //get all notes
        viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
            oldMyNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(), notesList)
            binding.rcvAllNotes.adapter = adapter
        })

        //filter all notes
        binding.allNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            })
        }

        //filter friends
        binding.filterFriends.setOnClickListener {
            viewModel.getFriendsNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            })
        }

        //filter work
        binding.filterWork.setOnClickListener {
            viewModel.getWorkNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            })
        }

        //filter personal
        binding.filterPersonal.setOnClickListener {
            viewModel.getPersonalNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            })
        }

        //filter home
        binding.filterHome.setOnClickListener {
            viewModel.getHomeNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            })
        }

        //filter family
        binding.filterFamily.setOnClickListener {
            viewModel.getFamilyNotes().observe(viewLifecycleOwner, { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            })
        }

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                NotesFilteting(newText)
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFilteting(query: String?) {
        val newFilterList = arrayListOf<Notes>()
        for (i in oldMyNotes) {
            if (i.title.contains(query!!, true) || i.subTitle.contains(query, true)) {
                newFilterList.add(i)
            }
        }
        adapter.filtering(newFilterList)
    }

}