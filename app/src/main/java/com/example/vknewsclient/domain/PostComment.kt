package com.example.vknewsclient.domain

import com.example.vknewsclient.R

data class PostComment(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatarId: Int = R.drawable.user_profile_male_icon,
    val commentText: String = "Ахаха, вот это прикол, конечно",
    val publicationDate: String = "14:00"
    )