package com.example.xcriticaltestapp

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xcriticaltestapp.databinding.FragmentProjectsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectsFragment : Fragment(), ProjectListAdapter.OnItemClickListener {

    private var _binding: FragmentProjectsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val projectListAdapter =
            viewModel.getAllProjects().value?.let { ProjectListAdapter(it, this) }

        viewModel.getAllProjects().observe(viewLifecycleOwner, {
            projectListAdapter?.setProjects(it)

            binding.recyclerViewProjects.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = projectListAdapter
                setHasFixedSize(true)
            }

            val swipeHandler = object : SwipeToDeleteCallback(context) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    projectListAdapter?.deleteProjectItem(viewHolder.absoluteAdapterPosition)
                }
            }

            val itemTouchHelper = ItemTouchHelper(swipeHandler)
            itemTouchHelper.attachToRecyclerView(binding.recyclerViewProjects)
            
        })
    }

    override fun onItemClick(position: Int) {
        findNavController().navigate(R.id.createProjectFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}