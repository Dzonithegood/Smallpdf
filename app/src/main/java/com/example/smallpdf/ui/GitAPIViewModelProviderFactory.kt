package com.example.smallpdf.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.smallpdf.repository.GitRepository

class GitAPIViewModelProviderFactory(
    val gitRepository: GitRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GitAPIViewModel(gitRepository) as T
    }
}