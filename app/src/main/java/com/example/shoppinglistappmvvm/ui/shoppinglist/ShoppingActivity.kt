package com.example.shoppinglistappmvvm.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistappmvvm.R
import com.example.shoppinglistappmvvm.data.db.ShoppingDatabase
import com.example.shoppinglistappmvvm.data.db.entities.ShoppingItem
import com.example.shoppinglistappmvvm.data.repositories.ShoppingRepository
import com.example.shoppinglistappmvvm.other.ShoppingItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        // this is very bad practice, its better if we have a global place object and from there we all pass the instance object.

        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        findViewById<RecyclerView>(R.id.rvShoppingItems).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.rvShoppingItems).adapter = adapter

        viewModel.getALlShoppingItem().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            AddShoppingItemDialog(this, object : AddDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}

/*
STEPS/NOTES:
1. First create a data class which stores the data and can also communicate with SQL also.. Here we create ShoppingItem as Data class.
2. Now tell Room, (The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access
while harnessing the full power of SQLite.)  how we want to access our database, so for that we need to create an interface called DAO(Data Access Object)
3. Inside that interface declare different method to access those databases like Insert, Delete, Update, Get all Shopping Item etc.
4. After dealing with the Dao interface, need to declare a database class. Here we used ShoppingDatabase.kt, it needs to be an abstract class
5. Now we want to organize our modals, as too many files many be generated and it is good practice to organize all those files in packages..
6.  After organizing all the stuffs, create your shopping repository class in the repository section, exL ShoppingRepository.kt
7. Now its time to create View Modal class, for that we create another package called ui and there we define a new ViewModel class named ShoppingViewModel.kt
8. Now we have to create factory to pass the constructor as ShoppingViewModel class has a constructor of type ShoppingRepository
9. So for that we created ShoppingViewModelFactory inside ui.shoppinglist package.
10. AFter instancing all the required Class items, now the time to bundle them and pass it to View Model Architecture using ViewModelProviders.of()
11. Now the time is to setup the RecyclerView along with its Adapters and its XML layouts..
12. After Creating the Seperate XML Layouts of each elements, like activity_shopping.xml, dialog_add_shopping_item.xml, shopping_item.xml
13. Now after that,
14. Now we want to display all the items of the Database in our recycler View, to do that, we write viewModel.getALlShoppingItem().observe(this, Observer {})
This basically means as, since getALlShoppingItem() has return type of LiveData<T> which has a function observe and Observer,
The observe simply oberve the data, and whenever the data changes, the code written in the Observer area gets excecuted.



To learn about Room and how to save data in a local database using Room
Reference : https://developer.android.com/training/data-storage/room
 */