package com.example.campcarrotmarket.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @param contentList 자신이 만든 게시물 아이디 리스트
 * @param likeList 좋아요 누른 게시물 아이디 리스트
 * */
@Parcelize
data class User(
    val id: Long = -1,
    val name: String = "",
    val address: String = "",
    val profileImageRes: Int = -1,
    val temperature: Float = 0f,
    val contentList: List<Long> = listOf(),
    val likeList: List<Long> = listOf()
): Parcelable
