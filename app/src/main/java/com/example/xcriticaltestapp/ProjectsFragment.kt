package com.example.xcriticaltestapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xcriticaltestapp.databinding.FragmentProjectsBinding

class ProjectsFragment : Fragment() {

    private var _binding: FragmentProjectsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProjectsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val testList = ArrayList<ProjectListItem>()
        testList.add(ProjectListItem(R.drawable.ic_mobile,"name1","text1"))
        testList.add(ProjectListItem(R.drawable.ic_mobile,"name2","text2"))
        testList.add(ProjectListItem(R.drawable.ic_mobile,"name3","text3"))
        testList.add(ProjectListItem(R.drawable.ic_mobile,"name4","text4"))
        testList.add(ProjectListItem(R.drawable.ic_mobile,"name5","text5"))
        testList.add(ProjectListItem(R.drawable.ic_mobile,"name6","text6"))
        testList.add(ProjectListItem(R.drawable.ic_mobile,"name7","text7"))
        testList.add(ProjectListItem(R.drawable.ic_mobile,"name8","text8"))
        testList.add(ProjectListItem(R.drawable.ic_mobile,"name9","text9"))
        testList.add(ProjectListItem(R.drawable.ic_mobile,"name10","text10"))
        testList.add(ProjectListItem(R.drawable.ic_mobile,"name11","text11"))

        binding.recyclerViewProjects.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ProjectListAdapter(testList)
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}