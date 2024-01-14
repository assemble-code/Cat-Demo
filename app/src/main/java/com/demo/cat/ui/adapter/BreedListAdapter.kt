package com.demo.cat.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.demo.cat.R
import com.demo.cat.databinding.BreedListItemBinding
import com.demo.cat.model.breedlistresponse.BreedListDataItem
import com.demo.cat.model.breedlistresponse.Image


class BreedListAdapter(private val data: List<BreedListDataItem>,private val listener: BreedListViewHolder.OnItemClick) :
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
        holder.setBreedData(breedData,listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class BreedListViewHolder(private val itemViewBinding: BreedListItemBinding) :
    RecyclerView.ViewHolder(itemViewBinding.root) {

    fun setBreedData(breedListDataItem: BreedListDataItem, listener: OnItemClick) {
        breedListDataItem.image = Image("",0,0, url="https://cdn2.thecatapi.com/images/${breedListDataItem.referenceImageId}.jpg")
        itemViewBinding.breedData = breedListDataItem
        //itemView.findViewById<ImageView>(R.id.ivCatImage).load(breedListDataItem.image?.url)

        itemView.setOnClickListener{
            listener.onCatBreedItemClick(breedListDataItem)
        }
    }



    interface OnItemClick{
      fun onCatBreedItemClick(breedListDataItem: BreedListDataItem)
    }

}