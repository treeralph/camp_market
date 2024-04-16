package com.example.campcarrotmarket.repository

import android.content.Context
import android.content.res.Resources
import com.example.campcarrotmarket.R
import com.example.campcarrotmarket.data.Content

class Repository(context: Context) {

    private val imageResources = listOf(
        R.drawable.sample1,
        R.drawable.sample2,
        R.drawable.sample3,
        R.drawable.sample4,
        R.drawable.sample5,
        R.drawable.sample6,
        R.drawable.sample7,
        R.drawable.sample8,
        R.drawable.sample9,
        R.drawable.sample10,
    )

    private val _contents = mutableListOf<Content>()
    val contents: List<Content> = _contents

    init {
        val contentTitles = context.resources.getStringArray(R.array.dummy_contents_title)
        val contentDescriptions = context.resources.getStringArray(R.array.dummy_contents_description)
        val contentUsers = context.resources.getStringArray(R.array.dummy_contents_user)
        val contentPrices = context.resources.getIntArray(R.array.dummy_contents_price)
        val contentAddresses = context.resources.getStringArray(R.array.dummy_contents_address)
        val contentNumLike = context.resources.getIntArray(R.array.dummy_contents_num_like)
        val contentNumChatting = context.resources.getIntArray(R.array.dummy_contents_num_chatting)

        repeat(10) { index ->
            _contents.add(
                Content(
                    id = (index + 1).toLong(),
                    title = contentTitles[index],
                    description = contentDescriptions[index],
                    address = contentAddresses[index],
                    price = contentPrices[index],
                    imageRes = imageResources[index],
                    user = contentUsers[index],
                    numChatting = contentNumChatting[index],
                    numLike = contentNumLike[index]
                )
            )
        }
    }


}