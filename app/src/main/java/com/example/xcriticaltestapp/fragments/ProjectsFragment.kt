package com.example.xcriticaltestapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xcriticaltestapp.models.MainViewModel
import com.example.xcriticaltestapp.adapters.ProjectListAdapter
import com.example.xcriticaltestapp.R
import com.example.xcriticaltestapp.SwipeToDeleteCallback
import com.example.xcriticaltestapp.databinding.FragmentProjectsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectsFragment : Fragment(), ProjectListAdapter.OnItemClickListener {

    private var _binding: FragmentProjectsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private val recyclerViewAdapter by lazy { ProjectListAdapter(viewModel.getAllProjects(), this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewProjects.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recyclerViewAdapter
            setHasFixedSize(true)
        }

        val swipeHandler = object : SwipeToDeleteCallback(context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.removeProject(
                    recyclerViewAdapter.deleteProjectItem(viewHolder.absoluteAdapterPosition)
                )
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewProjects)
    }

    override fun onItemClick(position: Int) {
        val selectedProject = recyclerViewAdapter.getProject(position)
        val bundle = bundleOf(
            "projectPosition" to position,
            "projectName" to selectedProject.projectName,
            "projectText" to selectedProject.projectText
        )
        findNavController().navigate(R.id.createProjectFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}