package com.example.xcriticaltestapp.fragments

import android.os.Bundle
import android.text.Editable
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.xcriticaltestapp.ProjectListItem
import com.example.xcriticaltestapp.R
import com.example.xcriticaltestapp.models.MainViewModel
import com.example.xcriticaltestapp.databinding.FragmentCreateProjectBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import kotlin.properties.Delegates

@AndroidEntryPoint
class CreateProjectFragment : Fragment() {

    private var param1: CharSequence? = null
    private var _binding: FragmentCreateProjectBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private var projectPosition by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateProjectBinding.inflate(inflater, container, false)
        binding.editTextProjectHeader.text = param1 as Editable?

        getProjectData()
        initializeListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeListeners() {
        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_createProjectFragment_to_projectsFragment)
        }
        binding.textViewSaveProject.setOnClickListener {
            if (this.arguments != null) {
                val bundle = bundleOf(
                    "projectPosition" to projectPosition,
                    "projectName" to binding.editTextProjectHeader.text.toString(),
                    "projectText" to binding.editTextScenario.text.toString(),
                    "projectDate" to LocalDateTime.now().toString()
                )

                findNavController().navigate(R.id.action_createProjectFragment_to_projectsFragment, bundle)
            } else {
                val newProject = ProjectListItem(
                    R.drawable.ic_mobile,
                    binding.editTextProjectHeader.text.toString(),
                    binding.editTextScenario.text.toString(),
                    LocalDateTime.now().toString()
                )

                viewModel.addProject(newProject)

                findNavController().navigate(R.id.action_createProjectFragment_to_projectsFragment)
            }
        }
    }

    private fun getProjectData() {
        if (this.arguments != null) {
            val args = this.arguments
            projectPosition = args?.get("projectPosition") as Int
            val projectName = args.get("projectName") as String
            val projectText = args.get("projectText") as String
            binding.editTextProjectHeader.setText(projectName)
            binding.editTextScenario.setText(projectText)
        }
    }
}