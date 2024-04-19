package com.example.campcarrotmarket

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.campcarrotmarket.adapter.ContentsRecyclerViewAdapter
import com.example.campcarrotmarket.data.Content
import com.example.campcarrotmarket.databinding.ActivityContentsBinding
import com.example.campcarrotmarket.repository.Repository

class ContentsActivity : AppCompatActivity() {

    companion object {
        lateinit var recyclerViewAdapter: ContentsRecyclerViewAdapter
    }

    private val binding by lazy { ActivityContentsBinding.inflate(layoutInflater) }
    private val repository by lazy { Repository(this) }

    private val exitDialogButtonClickListener = object: DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface?, which: Int) {
            when(which) {
                DialogInterface.BUTTON_POSITIVE -> finish()
                DialogInterface.BUTTON_NEGATIVE -> dialog?.dismiss()
            }
        }
    }

    private val exitDialogBuilder by lazy {
        AlertDialog.Builder(this).apply {
            setMessage(resources.getString(R.string.contents_exit_dialog_title))
            setPositiveButton(
                resources.getString(R.string.contents_exit_dialog_positive_text),
                exitDialogButtonClickListener
            )
            setNegativeButton(
                resources.getString(R.string.contents_exit_dialog_negative_text),
                exitDialogButtonClickListener
            )
        }
    }

    private val onBackPressedCallback = object: OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            exitDialogBuilder.show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onBackPressedDispatcher.addCallback(onBackPressedCallback)

        recyclerViewAdapter = ContentsRecyclerViewAdapter(
            items = repository.contents,
            itemClickListener = contentClickListener,
            itemLongClickListener =  contentLongClickListener
        )

        initView()
    }

    private fun initView() {
        with(binding) {
            contentsRecyclerView.adapter = recyclerViewAdapter
            contentsRecyclerView.addItemDecoration(
                DividerItemDecoration(this@ContentsActivity, LinearLayout.VERTICAL))
            contentsRecyclerView.layoutManager = LinearLayoutManager(this@ContentsActivity)
            notificationImageView.setOnClickListener(notificationImageviewClickListener)
            floatingActionButton.setOnClickListener{
                floatingActionButtonClickListener(contentsRecyclerView) }
        }
    }

    private val contentClickListener: (Content, Int) -> Unit = { content, index ->
        startActivity(
            Intent(this, ContentActivity::class.java).apply {
                putExtra(CONTENT_EXTRA, content)
                putExtra(CONTENT_INDEX_EXTRA, index)
            }
        )
    }

    private val contentLongClickListener: (Content, Int) -> Unit = { content, index ->

    }

    private val notificationImageviewClickListener: (View) -> Unit = {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!NotificationManagerCompat.from(this).areNotificationsEnabled()) {
                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                    putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
                }
                startActivity(intent)
            } else notification()
        }
    }

    private val floatingActionButtonClickListener: (RecyclerView) -> Unit = {
        it.scrollToPosition(0)
    }
}