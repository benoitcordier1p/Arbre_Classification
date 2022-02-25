package com.example.arbre_classification.util

import com.example.arbre_classification.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object ListTree : BottomNavItem("Trees", R.drawable.tree,"trees_list_screen")
    object AddTree: BottomNavItem("Add",R.drawable.circ,"add_tree_screen")

}