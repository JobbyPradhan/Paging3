package com.example.paging3

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3.databinding.ItemSelectBrandBinding


class ProductListAdapter(
) : PagingDataAdapter<AllBrand, ProductListAdapter.FlashSaleSelectProductHolder>(Diff) {

    private var index = -1



    inner class FlashSaleSelectProductHolder(private val binding : ItemSelectBrandBinding) : RecyclerView.ViewHolder(binding.root){

        @SuppressLint("NotifyDataSetChanged")
        fun bind(product : AllBrand){
            binding.tvProductName.text = product.name

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FlashSaleSelectProductHolder {
        return FlashSaleSelectProductHolder(
            ItemSelectBrandBinding.inflate(
                LayoutInflater.from(parent.context),parent,false))
    }



    object Diff: DiffUtil.ItemCallback<AllBrand>() {
        override fun areItemsTheSame(oldItem: AllBrand, newItem: AllBrand): Boolean = oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: AllBrand, newItem: AllBrand): Boolean = oldItem == newItem
    }


    override fun onBindViewHolder(holder: FlashSaleSelectProductHolder, position: Int) {
        val product = getItem(position)
        if (product != null) {
            holder.bind(product)
        }
    }
}