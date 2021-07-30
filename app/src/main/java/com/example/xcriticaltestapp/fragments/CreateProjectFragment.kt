package com.example.xcriticaltestapp.fragments

import android.os.Bundle
import android.text.Editable
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.xcriticaltestapp.models.MainViewModel
import com.example.xcriticaltestapp.databinding.FragmentCreateProjectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateProjectFragment : Fragment() {

    private var param1: CharSequence? = null
    private var _binding: FragmentCreateProjectBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

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

    private fun initializeListeners(){
        binding.backArrow.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun getProjectData(){
        if (this.arguments != null){
            val args = this.arguments
            val projectName = args?.get("projectName") as String
            val projectText = args.get("projectText") as String
            binding.editTextProjectHeader.setText(projectName)
            binding.editTextScenario.setText(projectText)
        }
    }
}