package com.example.appdatabinding.presentation.addTree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appdatabinding.R
import com.example.appdatabinding.databinding.FragmentAddFormBinding

class AddForm : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentAddFormBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_form, container, false
        )

        binding.ComposeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    Form(null)
                }
            }
        }
        binding.AddFormBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_addForm_to_addTreeFragment)
        }

        return binding.root
    }
}