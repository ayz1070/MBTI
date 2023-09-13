package com.example.mbti.model


import androidx.annotation.Keep
import java.io.Serializable
import java.util.Date

// 왜 null이여야 하는거지..?
@Keep
data class Post(
    var content:String? =null,
    var date: String?=null,
    var email:String?=null,
    var mbti:String?=null,
    var nickname: String?=null,
    var title:String?=null
): Serializable
