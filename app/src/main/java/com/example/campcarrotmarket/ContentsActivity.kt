package com.example.campcarrotmarket

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.window.OnBackInvokedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.campcarrotmarket.adapter.ContentsRecyclerViewAdapter
import com.example.campcarrotmarket.data.Content
import com.example.campcarrotmarket.data.User
import com.example.campcarrotmarket.databinding.ActivityContentsBinding
import com.example.campcarrotmarket.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ContentsActivity : AppCompatActivity() {

    companion object {
        lateinit var recyclerViewAdapter: ContentsRecyclerViewAdapter
    }

    private val binding by lazy { ActivityContentsBinding.inflate(layoutInflater) }
    private val repository by lazy { Repository(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerViewAdapter = ContentsRecyclerViewAdapter(repository.contents, contentClickListener)
        initView()
    }

    private fun initView() {
        with(binding) {
            contentsRecyclerView.adapter = recyclerViewAdapter
            contentsRecyclerView.addItemDecoration(
                DividerItemDecoration(this@ContentsActivity, LinearLayout.VERTICAL))
            contentsRecyclerView.layoutManager = LinearLayoutManager(this@ContentsActivity)
            notificationImageView.setOnClickListener(notificationImageviewClickListener)
            addressExpandImageView.setOnClickListener(addressExpandImageviewClickListener)
        }
    }

    private val contentClickListener: (Content, Int) -> Unit = { content, index ->
        startActivity(
            Intent(this, ContentActivity::class.java).apply {
                putExtra(CONTENT_EXTRA, content)
                putExtra(USER_EXTRA, User()) // todo: make user
                putExtra(CONTENT_INDEX_EXTRA, index)
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

    }
}