package com.demo.cat.network

import com.demo.cat.model.ResponseBaseClass

interface CatApi {

    suspend fun getBreedList(url:String) : ResponseBaseClass
}