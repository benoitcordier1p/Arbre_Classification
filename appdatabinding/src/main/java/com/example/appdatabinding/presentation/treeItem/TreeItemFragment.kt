package com.example.appdatabinding.presentation.treeItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.appdatabinding.R
import com.example.appdatabinding.databinding.FragmentTreeItemFragmentBinding
import com.example.domain.models.Tree

class TreeItemFragment : Fragment() {

    private val args by navArgs<TreeItemFragmentArgs>()
    lateinit var tree : Tree

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        tree = args.tree
        val binding: FragmentTreeItemFragmentBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_tree_item_fragment, container, false)
        binding.data = this

        return binding.root
    }

    fun goToListTree(){
        findNavController().navigate(R.id.action_treeItemFragment_to_listTreeFragment)
    }

    fun goToUpdateTree(){
        val action = TreeItemFragmentDirections.actionTreeItemFragmentToUpdateForm(tree)
        findNavController().navigate(action)
    }
}