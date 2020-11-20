package com.example.smallpdf.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide

import com.example.smallpdf.R
import com.example.smallpdf.adapters.GitRepoListAdapter
import com.example.smallpdf.models.UserReposItem
import com.example.smallpdf.repository.GitRepository
import com.example.smallpdf.util.Constants
import com.example.smallpdf.util.Resource
import kotlinx.android.synthetic.main.fragment_repo_list.*
import kotlinx.android.synthetic.main.fragment_user_details.*
import kotlinx.android.synthetic.main.repo_item.*
import kotlin.math.log

class RepoListFragment : Fragment(), GitRepoListAdapter.OnItemClicked2 {

    private var username: String? = null

    private var callback: OnNextClickedToCommitListener? = null


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

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)


//        buttonCommitDetails.setOnClickListener{
//            username?.let { it2 -> callback?.onNextClickedToCommit(it2) }
//        }

//    }

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
                    adapter.listener = this
                    response.data?.let { adapter.setList(it) }

                }
                is Resource.Loading -> {
                }
                else -> {
                    Log.v("UserDetails", "test")
                }
            }
        })

        username?.let { viewModel.getUserRepos(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        buttonCommitDetails.setOnClickListener{
//            username?.let { it2 -> callback?.onNextClickedToCommit(it2) }
//        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            callback = context as OnNextClickedToCommitListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
                    .toString() + " must implement LogoutUser"
            )
        }
    }

    interface OnNextClickedToCommitListener {
        fun onNextClickedToCommit(username: String)
    }

    override fun onItemClicked(userReposItem: UserReposItem) {
//        Toast.makeText(context, userReposItem.archiveUrl, Toast.LENGTH_SHORT).show()
        callback?.onNextClickedToCommit(userReposItem.branchesUrl)
    }
}
