package com.example.smallpdf.repository

import com.example.smallpdf.api.RetrofitInstance

class GitRepository {

suspend fun getUserDetails(userId:String) =
    RetrofitInstance.api.getUserDetails(userId)

    suspend fun getUserRepos(userId: String) =
        RetrofitInstance.api.getUserRepos(userId)

    suspend fun  getUserCommits(userId: String) =
        RetrofitInstance.api.getUserCommits(userId)

}