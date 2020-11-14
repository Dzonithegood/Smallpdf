package com.example.smallpdf.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.smallpdf.R
import com.example.smallpdf.repository.GitRepository

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_user_details)


        val gitRepository = GitRepository ()
        val viewModelProviderFactory = GitAPIViewModelProviderFactory(gitRepository)
        val viewModel = ViewModelProvider(this, viewModelProviderFactory).get(GitAPIViewModel::class.java)

        viewModel.userDetails.observe(this,{
            Toast.makeText(this, it.data?.htmlUrl,Toast.LENGTH_LONG).show()
        })
        viewModel.getUserDetails("octopod")
    }
}
