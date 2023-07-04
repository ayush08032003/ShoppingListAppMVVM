package com.example.shoppinglistappmvvm.data.repositories

import com.example.shoppinglistappmvvm.data.db.ShoppingDatabase
import com.example.shoppinglistappmvvm.data.db.entities.ShoppingItem

class ShoppingRepository(
    private val db : ShoppingDatabase
) {
    suspend fun upsert(item : ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item : ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItem()
}

/*
NOTES/STEPS:
1. Create shopping respository by defining all the neccessary queries which is implemented in DAO(Data Access Object)
Since it is small project the functions are low, but in bigger projects, functions count also increases, so it is good practice to define all
functions inside repository.

 */