package com.example.smallpdf.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.smallpdf.R
import com.example.smallpdf.repository.GitRepository
import com.example.smallpdf.util.Resource
import kotlinx.android.synthetic.main.fragment_user_details.*

private const val ARG_USERNAME = "username"

class UserDetailsFragment : Fragment() {

    private var username: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(ARG_USERNAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(username: String) =
            UserDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USERNAME, username)

                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val gitRepository = GitRepository()
        val viewModelProviderFactory = GitAPIViewModelProviderFactory(gitRepository)

        val viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(GitAPIViewModel::class.java)

        viewModel.userDetails.observe(viewLifecycleOwner, Observer { response ->

            when (response) {
                is Resource.Success -> {
                    name.text = response.data?.name
                    company.text = response.data?.subscriptionsUrl

                    context?.let { Glide.with(it).load(response.data?.avatarUrl).into(avatarImage) }
                }

                is Resource.Loading -> {
                }
                else -> {
                    Log.v("UserDetails","test")
                }
            }
        })

        username?.let { viewModel.getUserDetails(it) }
    }


}