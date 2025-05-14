package com.example.orderingfood.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.orderingfood.Domain.BannerModel
import com.example.orderingfood.Domain.CategoryModel
import com.example.orderingfood.Domain.FoodModel
import com.example.orderingfood.Repository.MainRepository

class MainViewModel:ViewModel() {
    private val repository = MainRepository()

    fun loadBanner(): LiveData<MutableList<BannerModel>> {
        return repository.loadBanner()
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()
    }

    fun loadFiltered(id:String): LiveData<MutableList<FoodModel>> {
        return repository.loadFiltered(id)
    }
}