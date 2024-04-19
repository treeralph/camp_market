package com.example.campcarrotmarket

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.campcarrotmarket.data.Content
import com.example.campcarrotmarket.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity() {

    private val binding by lazy { ActivityContentBinding.inflate(layoutInflater) }
    private val recyclerViewAdapter = ContentsActivity.recyclerViewAdapter
    private lateinit var content: Content
    private var contentIndex: Int = 0
    private var isLike = false

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        try {
            content = intent.getParcelableExtra(CONTENT_EXTRA, Content::class.java)!!
            contentIndex = intent.getIntExtra(CONTENT_INDEX_EXTRA, -1).also {
                if(it == -1) throw Exception()
            }
            initView()
        } catch(e: Exception) {
            // TODO: show snackbar
            Log.e("TAG", "onCreate: get extra data error")
            finish()
        }
    }

    private fun initView() {
        with(binding) {
            contentImageView.setImageResource(content.imageRes)
            userProfileImageview.setImageResource(R.drawable.user_icon)
            userNameTextView.text = content.user
            userAddressTextView.text = content.address
            userTemperatureTextView.text = temperatureFormatString(content.temperature)
            contentTitleTextView.text = content.title
            contentDescriptionTextView.text= content.description
            contentPriceTextView.text = currencyFormatString(content.price)
            contentChattingButton.setOnClickListener(contentChattingButtonClickListener)
            backButton.setOnClickListener(backButtonClickListener)
            contentLikeImageView.setOnClickListener(contentLikeImageViewClickListener)
            contentLikeImageView.setImageResource(
                if(isLike) R.drawable.ic_heart_filled
                else R.drawable.ic_heart
            )
        }
    }

    private val contentLikeImageViewClickListener: (View) -> Unit = {
        var flag = 1
        (it as ImageView).setImageResource(
            if(isLike) {
                flag = -1
                R.drawable.ic_heart
            } else R.drawable.ic_heart_filled
        )
        recyclerViewAdapter.itemChangedAt(
            contentIndex, content.copy(numLike = content.numLike + flag))
        isLike = !isLike
    }

    private val contentChattingButtonClickListener: (View) -> Unit = {

    }

    private val backButtonClickListener: (View) -> Unit = {
        finish()
    }
}