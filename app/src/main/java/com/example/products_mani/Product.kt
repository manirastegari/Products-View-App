package com.example.products_mani

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val products: List<Product>,
)
@Serializable
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val brand: String,
    val thumbnail: String,
    val discountPercentage:Double
)
