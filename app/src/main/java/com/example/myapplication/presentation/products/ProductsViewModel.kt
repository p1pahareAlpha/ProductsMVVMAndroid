package com.example.myapplication.presentation.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.local.ProductsDao
import com.example.myapplication.domain.products.model.Product
import com.example.myapplication.domain.products.model.ProductModel
import com.example.myapplication.domain.products.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val productsDao:  ProductsDao,// If direct DB Operations needed
) : ViewModel() {

    private val _productListMutableLiveData = MutableLiveData<ProductModel>()
    val productListLiveData: LiveData<ProductModel> = _productListMutableLiveData
    private val _selectedProductMutableLiveData = MutableLiveData<Product>()
    val selectedProductLiveData: LiveData<Product> = _selectedProductMutableLiveData
    private val _errorMessageLiveData = MutableLiveData<String>()
    val errorMessageLiveData: LiveData<String> = _errorMessageLiveData
    fun callProductListingAPI() {
        viewModelScope.launch {
            try {

                val result = productUseCase.getProducts()
                _productListMutableLiveData.value = result
            } catch (e: Exception) {
                e.printStackTrace()
              _errorMessageLiveData.value = e.toString()
            }
        }
    }

    fun callProductDetailsAPI(id:Int) {
        viewModelScope.launch {
            try {
                val result = productUseCase.getProductById(id)
                _selectedProductMutableLiveData.value = result
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessageLiveData.value = e.toString()
            }
        }
    }
}