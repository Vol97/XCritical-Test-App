package com.example.xcriticaltestapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xcriticaltestapp.databinding.FragmentProjectsBinding

class ProjectsFragment : Fragment(), ProjectListAdapter.OnItemClickListener {

    private var _binding: FragmentProjectsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val testList = ArrayList<ProjectListItem>()

        testList.add(ProjectListItem(R.drawable.ic_mobile, "name1", "text1"))
        testList.add(ProjectListItem(R.drawable.ic_mobile, "name2", "text2"))
        testList.add(ProjectListItem(R.drawable.ic_mobile, "name3", "text3"))
        testList.add(ProjectListItem(R.drawable.ic_mobile, "name4", "text4"))
        testList.add(ProjectListItem(R.drawable.ic_mobile, "name5", "text5"))
        testList.add(ProjectListItem(R.drawable.ic_mobile, "name6", "text6"))
        testList.add(ProjectListItem(R.drawable.ic_mobile, "name7", "text7"))
        testList.add(ProjectListItem(R.drawable.ic_mobile, "name8", "text8"))
        testList.add(ProjectListItem(R.drawable.ic_mobile, "name9", "text9"))
        testList.add(ProjectListItem(R.drawable.ic_mobile, "name10", "text10"))
        testList.add(ProjectListItem(R.drawable.ic_mobile, "name11", "text11"))

        val projectListAdapter = ProjectListAdapter(testList, this)

        binding.recyclerViewProjects.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = projectListAdapter
            setHasFixedSize(true)
        }

        val swipeHandler = object : SwipeToDeleteCallback(context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                projectListAdapter.deleteProjectItem(viewHolder.absoluteAdapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewProjects)
    }

    override fun onItemClick(position: Int) {
        findNavController().navigate(R.id.createProjectFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}