package com.demo.cat.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.demo.cat.R
import com.demo.cat.model.breedlistresponse.BreedListDataItem
import com.demo.cat.ui.adapter.BreedListAdapter
import com.demo.cat.viewmodel.CatBreedViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var catBreedViewModel: CatBreedViewModel
    private var pageNumber = 0
    private var limit = 0
    private lateinit var breedListAdapter: BreedListAdapter
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        catBreedViewModel = ViewModelProviders.of(this).get(CatBreedViewModel::class.java)
        catBreedViewModel.getCatBreed(pageNumber)

        initObserver()
    }

    private fun initObserver() {

        catBreedViewModel.getBreedListLiveData().observe(this, Observer { response ->

            if (response.responseCode == 200) {
                response.responseData?.let { data ->
                    val listType = Types.newParameterizedType(List::class.java, BreedListDataItem::class.java)
                    val moshiAdapter: JsonAdapter<List<BreedListDataItem>> =
                        moshi.adapter(listType)
                    val breedList:List<BreedListDataItem>? = moshiAdapter.fromJson(data)
                    breedList?.let { breedListAdapter = BreedListAdapter(breedList) }


                    rvBreedList.adapter = breedListAdapter
                }

            }
        }
        )
    }
}