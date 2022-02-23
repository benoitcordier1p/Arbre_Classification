package com.example.appdatabinding.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.appdatabinding.databinding.TreeItemBinding
import com.example.domain.models.Tree
import com.example.domain.models.mock


class TreeAdapter internal constructor(
    data: List<Tree>?) : RecyclerView.Adapter<TreeAdapter.TreeViewHolder?>() {


    private val mData: MutableList<Tree> = data?.toMutableList() ?: mutableListOf(mock())
    var onItemClick: ((Tree) -> Unit)? = null

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreeViewHolder {
        val mInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val treeItemBinding: TreeItemBinding = TreeItemBinding.inflate(mInflater, parent, false)
        return TreeViewHolder(treeItemBinding)
    }

    override fun onBindViewHolder(holder: TreeViewHolder, position: Int) {
        val tree: Tree = mData[position]
        holder.treeItem.tvTree.text = tree.id
        holder.treeItem.executePendingBindings()
    }

    override fun getItemCount() = mData.size

    inner class TreeViewHolder(treeItemBinding: TreeItemBinding) : RecyclerView.ViewHolder(treeItemBinding.root) {
        var treeItem: TreeItemBinding = treeItemBinding

        init {
            treeItemBinding.buttonTree.setOnClickListener {
                println(absoluteAdapterPosition.toString())
                onItemClick?.invoke(mData[absoluteAdapterPosition])
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newData: List<Tree>) {
        mData.clear()
        mData.addAll(newData)
        notifyDataSetChanged()
    }
}