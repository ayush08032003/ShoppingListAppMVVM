package com.example.shoppinglistappmvvm.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.example.shoppinglistappmvvm.data.db.entities.ShoppingItem
import com.example.shoppinglistappmvvm.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel (
    private val repository: ShoppingRepository): ViewModel()
{

        // this time upsert is not a suspend function as inside this we are going to define a coroutine
        fun upsert(item : ShoppingItem) = CoroutineScope(Dispatchers.Main).launch{
            repository.upsert(item)
        }
         fun delete(item :  ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
             repository.delete(item)
         }

        fun getALlShoppingItem()  = repository.getAllShoppingItems()


}

/*
NOTES/STEPS:
1. Here we define our functions which are defined in repository before because in view model, we are gonna actually call them.
 */