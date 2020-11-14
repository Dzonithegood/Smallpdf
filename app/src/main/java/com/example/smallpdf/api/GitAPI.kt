package com.example.smallpdf.api

import com.example.smallpdf.models.UserDetails
import com.example.smallpdf.models.UserRepos
import com.example.smallpdf.models.UserReposItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitAPI {

    @GET("users/{userID}")
    suspend fun getUserDetails(
        @Path("userID")
        userId: String
    ): Response<UserDetails>

    //    https://api.github.com/users/octocat/repos
    @GET("users/{userID}/repos")
    suspend fun getUserRepos(
        @Path("userID")
        userId: String
    ): Response<ArrayList<UserReposItem>>

    @GET("users/octocat")
    suspend fun getUserDetails1(
//        @Query("avatar")
//        avatarUrl: String,
//        @Query("name")
//        name: String,
//        @Query("company")
//        company: String,
        //ovo nisu request parametri zato mi ne trebaju
): Response<UserDetails>

@GET("users/octocat/repos")
suspend fun getUserRepositories1(
//        @Query("list")
//        UserRepos: ArrayList<UserReposItem>,
//        @Query("issuesNumber")
//        watchersCount: Int
): Response<UserRepos>
}