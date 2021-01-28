package com.demo.cat.network.repository

import com.demo.cat.model.ResponseBaseClass
import com.demo.cat.network.CatApi
import com.demo.cat.network.HttpNetwork

class CatApiImplementation : CatApi {

    override suspend fun getBreedList(url: String): ResponseBaseClass {

        val response = HttpNetwork.getData(url)

        return response
    }
}