package com.autodhil.musicapps.views.user

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.ACTION_LOCALE_SETTINGS
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.autodhil.musicapps.R
import com.autodhil.musicapps.databinding.FragmentUserBinding
import com.autodhil.musicapps.views.changepassword.ChangePasswordActivity
import com.autodhil.musicapps.views.edituser.EditUserActivity
import com.autodhil.musicapps.views.main.MainActivity
import org.jetbrains.anko.startActivity


class UserFragment : Fragment() {


    private var _binding: FragmentUserBinding? = null
    private val userBinding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
    }

    private fun onClick() {
        userBinding?.btnEditUser?.setOnClickListener {
            context?.startActivity<EditUserActivity>()
        }

        userBinding?.btnChangeLanguage?.setOnClickListener {
            startActivity(Intent(ACTION_LOCALE_SETTINGS))
        }

        userBinding?.btnChangePassword?.setOnClickListener {
            context?.startActivity<ChangePasswordActivity>()
        }

        userBinding?.btnLogout?.setOnClickListener {
            (activity as MainActivity).finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}