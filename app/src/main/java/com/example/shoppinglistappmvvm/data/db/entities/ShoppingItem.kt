package com.example.shoppinglistappmvvm.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="shopping_items")
data class ShoppingItem(
    @ColumnInfo(name="item_name")
    var name: String,
    @ColumnInfo(name="item_amount")
    var amount: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int?  = null
}
/*
NOTES:
Entity: Marks a class as an entity. This class will have a mapping SQLite table in the database. Basically works as a table..
Each entity must have at least 1 field annotated with PrimaryKey. You can also use primaryKeys() attribute to define the primary key.

 */