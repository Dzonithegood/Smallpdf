package com.example.smallpdf.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smallpdf.models.UserDetails
import com.example.smallpdf.models.UserRepos
import com.example.smallpdf.models.UserReposItem
import com.example.smallpdf.repository.GitRepository
import com.example.smallpdf.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class GitAPIViewModel(val gitRepository: GitRepository) : ViewModel() {

    val userDetails: MutableLiveData<Resource<UserDetails>> = MutableLiveData()

    var userDetailsResponse: UserDetails? = null

    val userRepos: MutableLiveData<Resource<ArrayList<UserReposItem>>> = MutableLiveData()

    var userReposResponse : UserRepos?=null


    val userCommits: MutableLiveData<Resource<UserReposItem>> = MutableLiveData()

    var userCommitsResponse : UserReposItem?=null



    fun getUserDetails(id:String) = viewModelScope.launch {

        userDetails.postValue(Resource.Loading())

            val response = gitRepository.getUserDetails(id)

        userDetails.postValue(handleUserDetailsResponse(response))

    }

    fun getUserRepos(id:String) = viewModelScope.launch {

        userRepos.postValue(Resource.Loading())

        val response =  gitRepository.getUserRepos(id)

        userRepos.postValue(handleUserReposResponse(response))

    }

    fun getUserCommits(id:String) = viewModelScope.launch {

        userCommits.postValue(Resource.Loading())

        val response =  gitRepository.getUserCommits(id)

        userCommits.postValue(handleUserCommitResponse(response))

    }

    private fun handleUserDetailsResponse(response : Response<UserDetails>) : Resource<UserDetails> {

        if (response.isSuccessful) {
            response.body()?.let { UserDetails ->
                return Resource.Success(userDetailsResponse ?: UserDetails)
            }
        }
            return Resource.Error(response.message())
        }

    private fun handleUserReposResponse(response : Response<ArrayList<UserReposItem>>) : Resource<ArrayList<UserReposItem>> {

        if (response.isSuccessful) {
            response.body()?.let { UserReposItem ->
                return Resource.Success(userReposResponse ?: UserReposItem)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleUserCommitResponse(response : Response<UserReposItem>) : Resource<UserReposItem> {

        if (response.isSuccessful) {
            response.body()?.let { UserReposItem ->
                return Resource.Success(userCommitsResponse ?: UserReposItem)
            }
        }
        return Resource.Error(response.message())
    }


}