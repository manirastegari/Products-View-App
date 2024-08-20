package com.example.products_mani

import retrofit2.http.GET
import retrofit2.http.Path


interface ApiInterface {
    @GET("products/category/{selectedCategory}")
    suspend fun categoryToFind(@Path("selectedCategory") selectedCategory: String): Category

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product
}