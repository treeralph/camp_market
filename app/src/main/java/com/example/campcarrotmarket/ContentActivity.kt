package com.example.campcarrotmarket

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.annotation.IntRange
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.campcarrotmarket.adapter.ContentsRecyclerViewAdapter
import com.example.campcarrotmarket.data.Content
import com.example.campcarrotmarket.data.User
import com.example.campcarrotmarket.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity() {

    private val binding by lazy { ActivityContentBinding.inflate(layoutInflater) }
    private val recyclerViewAdapter = ContentsActivity.recyclerViewAdapter

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        try {
            val content = intent.getParcelableExtra(CONTENT_EXTRA, Content::class.java)!!
            val user = intent.getParcelableExtra(USER_EXTRA, User::class.java)!!
            val contentIndex = intent.getIntExtra(CONTENT_INDEX_EXTRA, -1).also {
                if(it == -1) throw Exception()
            }
            initView(content, user, contentIndex)
        } catch(e: Exception) {
            // TODO: show snackbar
            Log.e("TAG", "onCreate: get extra data error")
            finish()
        }
    }

    private fun initView(content: Content, user: User, contentIndex: Int) {
        with(binding) {
            contentImageView.setImageResource(content.imageRes)
            userProfileImageview.setImageResource(user.profileImageRes)
            userNameTextView.text = user.name
            userAddressTextView.text = user.address
            userTemperatureTextView.text = user.temperature.toString()
            contentTitleTextView.text = content.title
            contentDescriptionTextView.text= content.description
            contentPriceTextView.text = content.price.toString()
            contentChattingButton.setOnClickListener(contentChattingButtonClickListener)
            contentLikeImageView.setOnClickListener{
                // TODO: Like
                // recyclerViewAdapter.notifyItemChanged(it, )
            }
        }
    }

    private val contentChattingButtonClickListener: (View) -> Unit = {
        // User().writeToParcel()
    }
}