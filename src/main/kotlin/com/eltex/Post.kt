package com.eltex


data class Post(
    val id: Long,
    val authorId: Long,
    val author: String,
    val authorJob: String?,
    val authorAvatar: String?,
    val content: String,
    val published: String,
    val coords: Coordinates?,
    val link: String?,
    val mentionedMe: Boolean,
    val likedByMe: Boolean,
    val attachment: Attachment?
)
