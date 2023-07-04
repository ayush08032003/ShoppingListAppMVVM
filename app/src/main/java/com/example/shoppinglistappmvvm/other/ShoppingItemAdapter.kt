package com.example.shoppinglistappmvvm.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistappmvvm.R
import com.example.shoppinglistappmvvm.data.db.entities.ShoppingItem
import com.example.shoppinglistappmvvm.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items : List<ShoppingItem>,
    private val viewModel: ShoppingViewModel // as we have to delete the data through database by staying inside the Adapter class
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    inner class ShoppingViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currShoppingItem = items[position]

        holder.itemView.findViewById<TextView>(R.id.tvName).text = currShoppingItem.name
        holder.itemView.findViewById<TextView>(R.id.tvAmount).text = "${currShoppingItem.amount}"

        holder.itemView.findViewById<ImageView>(R.id.ivDelete).setOnClickListener{
            viewModel.delete(currShoppingItem)
        }

        holder.itemView.findViewById<ImageView>(R.id.ivPlus).setOnClickListener{
            currShoppingItem.amount++
            viewModel.upsert(currShoppingItem)
        }

        holder.itemView.findViewById<ImageView>(R.id.ivMinus).setOnClickListener{
            if(currShoppingItem.amount > 0){
                currShoppingItem.amount--
                viewModel.upsert(currShoppingItem)
            }
        }


    }



}