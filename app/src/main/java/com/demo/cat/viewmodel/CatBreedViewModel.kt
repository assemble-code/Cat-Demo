package com.demo.cat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.cat.model.ResponseBaseClass
import com.demo.cat.network.CatApi
import com.demo.cat.network.repository.CatApiImplementation
import com.demo.cat.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray

class CatBreedViewModel : ViewModel() {
    private val catApi: CatApi = CatApiImplementation()
    private val ioDispatcher = Dispatchers.IO
    private val mainDispatcher = Dispatchers.Main
    private val breedListDataMutableLiveData = MutableLiveData<ResponseBaseClass>()

    fun getBreedListLiveData():MutableLiveData<ResponseBaseClass>{
        return  breedListDataMutableLiveData
    }

    fun getCatBreed(pageNumber: Int, limit: Int = 10) {
        viewModelScope.launch {
            lateinit var response: ResponseBaseClass
            lateinit var breedDataArray: JSONArray
            withContext(ioDispatcher) {
                response =
                    catApi.getBreedList("${Constants.baseURL}/breeds?limit=$limit&page=$pageNumber")
            }

            withContext(mainDispatcher) {

                breedListDataMutableLiveData.value = response
            }


        }

    }

}