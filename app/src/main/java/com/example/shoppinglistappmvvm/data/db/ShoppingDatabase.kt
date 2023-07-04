package com.example.shoppinglistappmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglistappmvvm.data.db.entities.ShoppingItem


@Database(
    entities = [ShoppingItem::class], // inside array as there could be many entities
    version = 1 // everytime when we need to change something in database, need to change the version, otherwise, Room will throw an Error
)
abstract class ShoppingDatabase : RoomDatabase() { // this class is inherited from RoomDatabase which helps to save data in local database
    abstract fun getShoppingDao() : ShoppingDao // with this function , we just make sure that we can access our database operation easily.. used in database repository

    companion object{ // create an instance of shopping database in inside the companion object, as it won't ,make any sense to have multiple instances of same database at a time
        // just to be make sure to have only one instance at a time

        @Volatile
        private var instance : ShoppingDatabase? = null
        private val LOCK = Any()

        // ShoppingDatabase()
        operator fun invoke(context : Context) = instance ?: synchronized(LOCK) { // sunchronized helps in taking lock so that no other threads read/write in it
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext, ShoppingDatabase::class.java, "ShoppingDB.db"
        ).build()
    }
}

/*
STEPS/NOTES:
1. The class needs to be an abstract class to make it workable and insert Database Annotation
2. Companion object is basically same as static keyword in java.
3. @Volatile:  reads and writes to this field are atomic and writes are always made visible to other threads. If another thread reads
the value of this field (e.g. through its accessor), it sees not only that value, but all side effects that led to writing that value.
4. Room.databaseBuilder(@NonNull android.content.Context context,
    @NonNull Class<T> klass,
    @NonNull String name) :
Creates a RoomDatabase.Builder for a persistent database. Once a database is built, you should keep a reference to it and re-use it.
Params:
context – The context for the database. This is usually the Application context.
klass – The abstract class which is annotated with Database and extends RoomDatabase.
name – The name of the database file.
5. Now create an operator function to invoke, means this function is executed whenever we create an instance of shopping database class
operator fun invoke(context : Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabse(context).also { instance = it }
        }

First it will check if the instance is null or not, if it is not null, it wil directly share the instance, otherwise it TAKES lock, ie no other
thread can takes or read instance variable. In inner line, if its still null, the function will assign the Room.databaseBuilder object, that if defined
before this
 */