package com.demo.cat.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.cat.R
import com.demo.cat.databinding.BreedListItemBinding
import com.demo.cat.model.breedlistresponse.BreedListDataItem
import com.demo.cat.model.breedlistresponse.Image
import com.demo.cat.viewmodel.CatBreedViewModel


class BreedListAdapter(
    private var data: List<BreedListDataItem>,
    private val listener: BreedListViewHolder.OnItemClick
) :
    RecyclerView.Adapter<BreedListViewHolder>(), Filterable {
    var dataFilter: List<BreedListDataItem> = ArrayList(data)

    private lateinit var catListFilter: CatListFilter
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
        holder.setBreedData(breedData, listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getFilter(): Filter {
        if (!::catListFilter.isInitialized) {
            catListFilter = CatListFilter()
        }
        return catListFilter
    }
    inner class CatListFilter : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResult = FilterResults()

            constraint?.let {
                if (it.isNotEmpty()) {
                    val filterList: MutableList<BreedListDataItem> = ArrayList()
                    for (cat in dataFilter) {
                        if (cat.name?.contains(constraint.toString(), true) == true ||
                            cat.origin?.contains(constraint.toString(), true) == true
                        ) {
                            filterList.add(cat)
                        }
                    }
                    filterResult.count = filterList.size
                    filterResult.values = filterList

                } else {
                    filterResult.count = dataFilter.size
                    filterResult.values = dataFilter
                }
            }
            return filterResult
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            data = results?.values as List<BreedListDataItem>
            notifyDataSetChanged()
        }
    }
}

class BreedListViewHolder(private val itemViewBinding: BreedListItemBinding) :
    RecyclerView.ViewHolder(itemViewBinding.root) {

    fun setBreedData(breedListDataItem: BreedListDataItem, listener: OnItemClick) {
        breedListDataItem.image = Image(
            "",
            0,
            0,
            url = "https://cdn2.thecatapi.com/images/${breedListDataItem.referenceImageId}.jpg"
        )
        itemViewBinding.breedData = breedListDataItem

        itemView.setOnClickListener {
            listener.onCatBreedItemClick(breedListDataItem)
        }
    }


    interface OnItemClick {
        fun onCatBreedItemClick(breedListDataItem: BreedListDataItem)
    }

}