package com.example.expiryreminderapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expiryreminderapp.modal.Product
import kotlinx.android.synthetic.main.activity_addproduct.view.*

class ProductRecyclerAdapter(private val listProducts: List<Product>) : RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        // inflating recycler item view
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_recycler, parent, false)

        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.textViewProName.text = listProducts[position].productname
        holder.textViewDate.text = listProducts[position].expirydate

    }


    override fun getItemCount(): Int = listProducts.size

    /**
     * ViewHolder class
     */
     class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var textViewProName: AppCompatTextView
        var textViewDate: AppCompatTextView


        init {
            textViewProName = view.findViewById(R.id.proname) as AppCompatTextView
            textViewDate = view.findViewById(R.id.prodate) as AppCompatTextView

        }
    }

}