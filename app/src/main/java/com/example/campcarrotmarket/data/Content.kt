package com.example.campcarrotmarket.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * @param address 희망 거래 지역
 * @param price 희망 가격
 * @param imageResList 이미지 리스트( contents에서는 0-index 사용 )
 * @param chattingList 채팅룸 아이디 리스트
 * @param likeList 좋아요를 누른 유저의 아이디 리스트
 * */
@Parcelize
data class Content(
    val id: Long = -1,
    val title: String = "",
    val description: String = "",
    val address: String = "",
    val price: Int = -1,
    val imageRes: Int = -1,
    val chattingList: List<Long> = listOf(),
    val likeUserList: List<Long> = listOf()
): Parcelable
