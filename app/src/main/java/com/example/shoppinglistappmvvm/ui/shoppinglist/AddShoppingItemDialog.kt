package com.example.shoppinglistappmvvm.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglistappmvvm.R
import com.example.shoppinglistappmvvm.data.db.entities.ShoppingItem

// context ->  current state of the application/object.t lets newly-created objects understand what has been going on.
// Typically, you call it to get information regarding another part of your program (activity and package/application).
class AddShoppingItemDialog(context : Context, var addDialogListener: AddDialogListener) : AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        val tvAdd = findViewById<TextView>(R.id.tvAdd)
        val tvCancel = findViewById<TextView>(R.id.tvCancel)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val etName = findViewById<EditText>(R.id.etName)
        val etAmount = findViewById<EditText>(R.id.etAmount)

        tvAdd?.setOnClickListener {
            val name = etName?.text.toString()
            val amount = etAmount?.text.toString()

            if(name.isEmpty() || amount.isEmpty()){
                Toast.makeText(context, "Please Enter all the information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val item =  ShoppingItem(name,amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        tvCancel?.setOnClickListener{
            cancel()
        }




    }
}