package com.demo.cat.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.demo.cat.R
import com.demo.cat.databinding.BreedListItemBinding
import com.demo.cat.model.breedlistresponse.BreedListDataItem
import kotlinx.android.synthetic.main.breed_list_item.view.*

class BreedListAdapter(private val data: List<BreedListDataItem>) :
    RecyclerView.Adapter<BreedListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<BreedListItemBinding>(
            inflater,
            R.layout.breed_list_item,
            parent,
            false
        )
        return BreedListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreedListViewHolder, position: Int) {
        val breedData = data[holder.adapterPosition]
        holder.setBreedData(breedData)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class BreedListViewHolder(private val itemViewBinding: BreedListItemBinding) :
    RecyclerView.ViewHolder(itemViewBinding.root) {

    fun setBreedData(breedListDataItem: BreedListDataItem) {
        itemViewBinding.breedData = breedListDataItem
        itemView.ivCatImage.load(breedListDataItem.image?.url)
    }

}