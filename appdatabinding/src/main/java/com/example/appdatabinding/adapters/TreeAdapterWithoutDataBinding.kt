package com.example.appdatabinding.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appdatabinding.R
import com.example.domain.models.Tree


class TreeAdapterWithoutDataBinding internal constructor(context: Context?, data: List<Tree>) :
    RecyclerView.Adapter<TreeAdapterWithoutDataBinding.ViewHolder>() {

    private val mData: MutableList<Tree> = data.toMutableList()
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    var onItemClick: ((Tree) -> Unit)? = null

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.tree_item, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tree = mData[position]
        holder.myTextView.text = tree.id
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        var myTextView: TextView = itemView.findViewById(R.id.tvTree)

        init {
            itemView.findViewById<Button>(R.id.buttonTree).setOnClickListener {
                onItemClick?.invoke(mData[absoluteAdapterPosition])
            }
        }
    }

    /*
    fun submitList(newData: List<Tree>) {
        mData.clear()
        mData.addAll(newData)
        notifyDataSetChanged()
    }
    */



}


