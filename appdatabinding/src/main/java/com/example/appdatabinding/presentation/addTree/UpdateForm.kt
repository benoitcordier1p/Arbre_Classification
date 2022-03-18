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
import androidx.navigation.fragment.navArgs
import com.example.appdatabinding.R
import com.example.appdatabinding.databinding.FragmentUpdateFormBinding
import com.example.appdatabinding.presentation.treeItem.TreeItemFragmentArgs
import com.example.domain.models.Tree


class UpdateForm : Fragment() {

    private val args by navArgs<TreeItemFragmentArgs>()
    private var tree : Tree? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        tree = args.tree
        val binding: FragmentUpdateFormBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_update_form, container, false
        )

        binding.UpdateFormBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_updateForm_to_addTreeFragment)
        }

        binding.ComposeViewUpdate.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    Form(tree)
                }
            }
        }

        return binding.root
    }
}