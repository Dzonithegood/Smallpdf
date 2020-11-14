package com.example.smallpdf.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.smallpdf.R
import com.example.smallpdf.repository.GitRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),LoginFragment.OnLoggingClickedListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onLoggingClicked(username: String) {
        Toast.makeText(this, "Username$username",Toast.LENGTH_LONG).show()

        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.add(R.id.fragments_holder, UserDetailsFragment.newInstance(username), "your frame name")
        ft.addToBackStack(null)
        ft.commit()
    }
}
