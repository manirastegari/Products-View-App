package com.example.products_mani

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.products_mani.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter
    private var dataToDisplay: MutableList<Product> = mutableListOf()
    var api = RetrofitInstance.api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = MyAdapter(dataToDisplay) { product ->
            val intent = Intent(this, ProductDetailsActivity::class.java)
            intent.putExtra("PRODUCT_ID", product.id)
            startActivity(intent)
        }
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter
        binding.rv.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        val categories = listOf("smartphones", "laptops", "skincare", "groceries")
        val spinnerAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, categories)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = spinnerAdapter
        binding.button.setOnClickListener {
            val selectedCategory = binding.spinner.selectedItem.toString()
            lifecycleScope.launch {
                try {
                    val categoryObject :Category = api.categoryToFind(selectedCategory)
                    val productsList:List<Product> = categoryObject.products
                    for (x: Product in productsList) {
                        Log.d("TEST", "Product description: ${x.description}")
                        Log.d("TEST", "Discount: ${x.discountPercentage}")
                    }
                    dataToDisplay.clear()
                    dataToDisplay.addAll(productsList)
                    adapter.notifyDataSetChanged()
                } catch (e: Exception) {
                    Log.e("+++ERROR", "Error", e)
                }
            }
        }
    }
}