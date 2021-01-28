package com.demo.cat.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.demo.cat.R
import com.demo.cat.databinding.ActivityBreedDetailBinding
import com.demo.cat.model.breedlistresponse.BreedListDataItem

class BreedDetail : AppCompatActivity() {
    private lateinit var dataBinding: ActivityBreedDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding=    DataBindingUtil.setContentView<ActivityBreedDetailBinding>(this,R.layout.activity_breed_detail)

        //get the data from the intent
        val breedListDataItem:BreedListDataItem? =  intent.getParcelableExtra("data") as BreedListDataItem?
        //set the data to view
        dataBinding.breedData = breedListDataItem

    }
}