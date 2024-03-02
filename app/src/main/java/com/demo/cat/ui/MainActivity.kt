package com.demo.cat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.demo.cat.R
import com.demo.cat.model.breedlistresponse.BreedListDataItem
import com.demo.cat.network.HttpNetwork
import com.demo.cat.ui.adapter.BreedListAdapter
import com.demo.cat.ui.adapter.BreedListViewHolder
import com.demo.cat.viewmodel.CatBreedViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


class MainActivity : AppCompatActivity(), BreedListViewHolder.OnItemClick {
    private lateinit var catBreedViewModel: CatBreedViewModel
    private var pageNumber = 0
    private lateinit var breedListAdapter: BreedListAdapter
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    lateinit var rvBreedList: RecyclerView
    lateinit var progress: ProgressBar
    lateinit var search: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvBreedList = findViewById(R.id.rvBreedList)
        progress = findViewById(R.id.progress)
        search = findViewById(R.id.etSearch)
        catBreedViewModel = ViewModelProviders.of(this).get(CatBreedViewModel::class.java)
        //load the initial data
        if (HttpNetwork.isNetworkConnected(this)) {
            catBreedViewModel.getCatBreed(pageNumber)
        } else {
            Toast.makeText(
                this,
                getString(R.string.please_check_your_internet_connection),
                Toast.LENGTH_SHORT
            ).show()
        }

        initObserver()


        search.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
               return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                Log.d(this::class.java.toString(),"Searching for Character $p0")
                breedListAdapter.filter.filter(p0)
                return true
            }


        })


    }

    private fun initObserver() {

        catBreedViewModel.getBreedListLiveData().observe(this, Observer { response ->
            if (response.responseCode == 200) {
                response.responseData?.let { data ->
                    //convert the data to model class using moshi adapter
                    val listType =
                        Types.newParameterizedType(List::class.java, BreedListDataItem::class.java)
                    val moshiAdapter: JsonAdapter<List<BreedListDataItem>> = moshi.adapter(listType)
                    val breedList: List<BreedListDataItem>? = moshiAdapter.fromJson(data)
                    breedList?.let {
                        breedListAdapter = BreedListAdapter(breedList, this@MainActivity)
                    }
                    rvBreedList.adapter = breedListAdapter
                }

            } else {
                Toast.makeText(this, getString(R.string.unable_to_load_data), Toast.LENGTH_SHORT)
                    .show()
            }
        }
        )


        catBreedViewModel.getIsLoading().observe(this, Observer { isLoading ->

            progress.visibility = if (isLoading) View.VISIBLE else View.GONE

        })
    }

    override fun onCatBreedItemClick(breedListDataItem: BreedListDataItem) {
        //start the detail page
        val intent = Intent(this, BreedDetail::class.java)
        intent.putExtra("data", breedListDataItem)
        startActivity(intent)
    }
}