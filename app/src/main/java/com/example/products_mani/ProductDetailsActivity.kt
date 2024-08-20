package com.example.products_mani

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.products_mani.databinding.ActivityProductDetailsBinding
import kotlinx.coroutines.launch

class ProductDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductDetailsBinding
    var api = RetrofitInstance.api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
            val Id = intent.getIntExtra("PRODUCT_ID", -1)
                lifecycleScope.launch {
                    val product = api.getProductById(Id)

                    binding.Title.text = product.title
                    binding.Description.text = product.description
                    val discountedPrice = product.price * (1 - product.discountPercentage / 100.0)
                    binding.Price.text = "Price: $${product.price}\nDiscount %${product.discountPercentage}\nTotal Price $${discountedPrice}"
                    binding.Brand.text = "Brand: ${product.brand}"
                    Glide.with(this@ProductDetailsActivity)
                        .load(product.thumbnail)
                        .into(binding.Image)
        }
    }
}
