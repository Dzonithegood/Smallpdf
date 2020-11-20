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

import com.example.smallpdf.R
import com.example.smallpdf.adapters.GitRepoListAdapter
import com.example.smallpdf.models.UserReposItem
import com.example.smallpdf.repository.GitRepository
import com.example.smallpdf.util.Constants
import com.example.smallpdf.util.Resource
import kotlinx.android.synthetic.main.fragment_commit_details.*
import kotlinx.android.synthetic.main.fragment_repo_list.*


class CommitDetailsFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_commit_details, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(username: String) =
            CommitDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.ARG_USERNAME, username)

                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        val adapter = GitRepoListAdapter()
//        repoList.adapter = adapter
//        repoList.layoutManager = LinearLayoutManager(context)

        val gitRepository = GitRepository()
        val viewModelProviderFactory = GitAPIViewModelProviderFactory(gitRepository)

        val viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(GitAPIViewModel::class.java)

        viewModel.userCommits.observe(viewLifecycleOwner, Observer { response ->

            when (response) {
                is Resource.Success -> {
                    test1.text = response.data?.defaultBranch

                }
                is Resource.Loading -> {
                }
                else -> {
                    Log.v("CommitDetails","test1")
                }
            }
        })

        username?.let { viewModel.getUserCommits(it) }
    }

}
