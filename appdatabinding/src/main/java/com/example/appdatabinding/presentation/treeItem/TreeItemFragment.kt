package com.example.appdatabinding.presentation.treeItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.appdatabinding.MainActivity
import com.example.appdatabinding.R
import com.example.appdatabinding.databinding.FragmentTreeItemFragmentBinding

class TreeItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val act = activity as MainActivity
        val binding: FragmentTreeItemFragmentBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_tree_item_fragment, container, false)
        binding.treeItem = act.vm//attach your viewModel to xml
        binding.main = act
        return binding.root

    }
}