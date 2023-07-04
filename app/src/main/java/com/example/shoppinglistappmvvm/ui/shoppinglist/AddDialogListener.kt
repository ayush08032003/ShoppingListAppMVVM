package com.example.shoppinglistappmvvm.ui.shoppinglist

import com.example.shoppinglistappmvvm.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item : ShoppingItem)
}