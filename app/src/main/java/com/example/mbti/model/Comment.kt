package com.example.mbti.model

data class Comment(
    var nickname:String? = null,
    var mbti:String?= null,
    var content: String?= null,
    var userId: String?= null,
    var timestamp: String?= null,
    var docId: String? =null
)
