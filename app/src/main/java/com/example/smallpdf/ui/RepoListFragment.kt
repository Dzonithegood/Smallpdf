package com.example.smallpdf.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide

import com.example.smallpdf.R
import com.example.smallpdf.adapters.GitRepoListAdapter
import com.example.smallpdf.repository.GitRepository
import com.example.smallpdf.util.Constants
import com.example.smallpdf.util.Resource
import kotlinx.android.synthetic.main.fragment_repo_list.*
import kotlinx.android.synthetic.main.fragment_user_details.*

class RepoListFragment : Fragment() {

    private var username: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(Constants.ARG_USERNAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(username: String) =
            RepoListFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.ARG_USERNAME, username)

                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = GitRepoListAdapter()
        repoList.adapter = adapter
        repoList.layoutManager = LinearLayoutManager(context)

        val gitRepository = GitRepository()
        val viewModelProviderFactory = GitAPIViewModelProviderFactory(gitRepository)

        val viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(GitAPIViewModel::class.java)

        viewModel.userRepos.observe(viewLifecycleOwner, Observer { response ->

            when (response) {
                is Resource.Success -> {
//                    name.text = response.data?.name
                    response.data?.let { adapter.setList(it) }

                }
                is Resource.Loading -> {
                }
                else -> {
                    Log.v("UserDetails","test")
                }
            }
        })

        username?.let { viewModel.getUserRepos(it) }
    }

}
