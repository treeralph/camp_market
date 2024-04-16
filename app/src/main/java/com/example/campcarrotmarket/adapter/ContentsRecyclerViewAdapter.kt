package com.example.campcarrotmarket.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.campcarrotmarket.data.Content
import com.example.campcarrotmarket.databinding.ContentsItemBinding

class ContentsRecyclerViewAdapter(
    private val items: List<Content>,
    private val itemClickListener: (Content, Int) -> Unit,
): RecyclerView.Adapter<ContentsRecyclerViewAdapter.ContentsViewHolder>() {

    private val itemList = mutableListOf<Content>()
    init { itemList.addAll(items) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentsViewHolder {
        val binding = ContentsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentsViewHolder, position: Int) {
        val currentItem = itemList[position]
        with(holder.binding) {
            contentsImageView.setImageResource(currentItem.imageRes)
            contentsTitleTextView.text = currentItem.title
            contentsAddressTextView.text = currentItem.address
            contentsPriceValueTextView.text = currentItem.price.toString() // todo: number format
            contentsCountChattingTextView.text = currentItem.numChatting.toString()
            contentsCountHeartTextView.text = currentItem.numLike.toString()
            root.setOnClickListener { itemClickListener(currentItem, position) }
        }
    }

    override fun onBindViewHolder(
        holder: ContentsViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if(payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            for(payload in payloads) {
                Log.i("TAG", "onBindViewHolder: payload: $payload")
                with(holder.binding) {
                    contentsPriceValueTextView.text = payload.toString()
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ContentsViewHolder(
        val binding: ContentsItemBinding
    ): ViewHolder(binding.root)
}