package com.example.products_mani

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.products_mani.databinding.TextAndImageRowLayoutBinding
class MyAdapter(
    private val products: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<MyAdapter.ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = TextAndImageRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            onItemClick(product)
        }
    }
    override fun getItemCount(): Int {
        val size = products.size
        return size
    }
    class ProductViewHolder(private val binding: TextAndImageRowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.Title.text = product.title
            binding.Price.text = "Price: $${product.price}"
        }
    }
}