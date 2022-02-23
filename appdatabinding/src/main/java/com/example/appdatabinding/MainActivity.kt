package com.example.appdatabinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.appdatabinding.presentation.treeItem.TreeItemFragment
import com.example.appdatabinding.presentation.treeList.ListTreeFragment
import com.example.appdatabinding.presentation.treeList.TreesListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var vm: TreesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this)[TreesListViewModel::class.java]

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_frag, ListTreeFragment())
            commit()
        }
    }

    fun onSelectedItem() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_frag, TreeItemFragment())
            commit()
        }
    }

    fun backMenu() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_frag, ListTreeFragment())
            commit()
        }
    }
}