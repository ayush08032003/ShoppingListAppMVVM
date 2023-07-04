package com.example.shoppinglistappmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppinglistappmvvm.data.db.entities.ShoppingItem

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // this annotation helps on insertion as well as for updation.
    suspend fun upsert(item : ShoppingItem)

    @Delete
    suspend fun delete(item : ShoppingItem)

    @Query("SELECT * FROM shopping_items") // need to give query, on what it is called..
    fun getAllShoppingItem () : LiveData<List<ShoppingItem>> // Just put the return type inside the LiveData Object


}
/*
NOTES:
1. Inn Sql, it does not allow to write to the database in main thread, so we have to call these above functions asynchronously. which basiclly means that
we either have to use Thread or in kotlin, Coroutine
2. LiveData has the knowledge about the Life cycle of its observers like activity or fragment. That means Live data only updates the app components
like Activity or Fragments which are in active life cycle state. It makes it really efficient to update our recycler view later on
 */