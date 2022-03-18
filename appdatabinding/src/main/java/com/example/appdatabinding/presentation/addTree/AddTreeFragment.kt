package com.example.appdatabinding.presentation.addTree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appdatabinding.R
import com.example.appdatabinding.databinding.FragmentAddTreeBinding
import com.example.domain.models.mock

class AddTreeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentAddTreeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_tree, container, false
        )
        binding.data=this
        return binding.root
    }

    fun toUpdate(){
        val action = AddTreeFragmentDirections.actionAddTreeFragmentToUpdateForm(mock())
        findNavController().navigate(action)
    }

    fun toAdd(){
        findNavController().navigate(R.id.action_addTreeFragment_to_addForm)
    }
}