package com.example.campcarrotmarket

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.window.OnBackInvokedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.campcarrotmarket.adapter.ContentsRecyclerViewAdapter
import com.example.campcarrotmarket.data.Content
import com.example.campcarrotmarket.data.User
import com.example.campcarrotmarket.databinding.ActivityContentsBinding

class ContentsActivity : AppCompatActivity() {

    private val binding: ActivityContentsBinding by lazy {
        ActivityContentsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {

        val testContents = mutableListOf<Content>()
        repeat(30) {
            testContents.add(
                Content(
                    title = "",
                    imageRes = R.drawable.image_placeholder
                )
            )
        }

        // todo: getData
        val recyclerViewAdapter = ContentsRecyclerViewAdapter(testContents, contentClickListener)
        with(binding) {
            contentsRecyclerView.adapter = recyclerViewAdapter
            contentsRecyclerView.layoutManager = LinearLayoutManager(this@ContentsActivity)
            notificationImageView.setOnClickListener(notificationImageviewClickListener)
            addressExpandImageView.setOnClickListener(addressExpandImageviewClickListener)
        }
    }

    private val contentClickListener: (Content) -> Unit = {
        startActivity(
            Intent(this, ContentActivity::class.java).apply {
                putExtra(CONTENT_EXTRA, it)
            }
        )
    }

    private val notificationImageviewClickListener: (View) -> Unit = {
        // todo: make notification
    }

    private val addressExpandImageviewClickListener: (View) -> Unit = {
        // todo: locate list
    }

    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
        return super.getOnBackInvokedDispatcher()
        // todo: 종료 여부 AlertDialog
    }
}