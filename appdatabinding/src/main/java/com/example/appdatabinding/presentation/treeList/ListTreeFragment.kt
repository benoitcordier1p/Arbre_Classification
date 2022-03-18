package com.example.appdatabinding.presentation.treeList

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appdatabinding.MainActivity
import com.example.appdatabinding.R
import com.example.appdatabinding.adapters.TreeAdapter
import com.example.appdatabinding.databinding.FragmentListTreeBinding


class ListTreeFragment : Fragment() {

    private var treeAdapter: TreeAdapter? = null
    lateinit var act: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        act = (activity as MainActivity)
        val binding: FragmentListTreeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list_tree, container, false)

        val view: View = binding.root
        binding.vm = act.vm
        binding.lifecycleOwner = this

        recyclerViewSetUp(act, binding)
        act.vm.state.observe(viewLifecycleOwner) {
            treeAdapter?.submitList(it)
        }

        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                act.vm.getTrees()
            }
        })
        return view
    }

    private fun recyclerViewSetUp(act: MainActivity, binding: FragmentListTreeBinding) {

        val recyclerView: RecyclerView = binding.rvTree
        treeAdapter = act.vm.state.value?.let { TreeAdapter(it) }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = treeAdapter
        }

        treeAdapter?.onItemClick = { tree ->
            val action = ListTreeFragmentDirections.actionListTreeFragmentToTreeItemFragment(tree)
            findNavController().navigate(action)
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && !act.vm.lastTree.value!!) {
                    act.vm.getTrees()
                }
            }
        })
    }
}