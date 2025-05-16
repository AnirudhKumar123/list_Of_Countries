package com.action.walmart_assesment.itemList.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.action.walmart_assesment.databinding.CountryItemBinding
import com.action.walmart_assesment.itemList.data.remote.dtos.Country

class ItemListAdapter(
    private var data: List<Country>
): RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {
    class ViewHolder(
        private val binding: CountryItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        lateinit var name: TextView
        lateinit var region: TextView
        lateinit var code: TextView
        lateinit var capital: TextView
        fun bind(countryItem: Country) {
            binding.apply {
                name = tvName
                region = tvRegion
                code = tvCode
                capital = tvCapital
            }
            name.text = countryItem.name + ", "+countryItem.region
            //region.text = countryItem.region
            code.text = countryItem.code
            capital.text = countryItem.capital
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CountryItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCountry = data[position]
        holder.bind(currentCountry)
    }
    fun updateData(newList: List<Country>) {
        data = newList
        notifyDataSetChanged()
    }
}