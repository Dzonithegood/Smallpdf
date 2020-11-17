package com.example.smallpdf.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smallpdf.R
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private var callback: OnLoggingClickedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnlogin.setOnClickListener {
            callback?.onLoggingClicked(PersonName.text.toString())
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            callback = context as OnLoggingClickedListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
                    .toString() + " must implement LogoutUser"
            )
        }
    }

    interface OnLoggingClickedListener {
        fun onLoggingClicked(username: String)
    }
}