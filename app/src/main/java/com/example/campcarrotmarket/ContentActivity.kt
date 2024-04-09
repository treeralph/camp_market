package com.example.campcarrotmarket

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.campcarrotmarket.adapter.ContentsRecyclerViewAdapter
import com.example.campcarrotmarket.data.Content
import com.example.campcarrotmarket.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity() {

    private val binding: ActivityContentBinding by lazy {
        ActivityContentBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // todo: get parcelable extra Content, User

        initView()
    }

    private fun initView() {

    }
}