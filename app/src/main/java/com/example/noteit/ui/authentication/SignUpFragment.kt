package com.example.noteit.ui.authentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteit.R
import com.example.noteit.core.utils.SessionManager
import com.example.noteit.databinding.FragmentCheckBinding
import com.example.noteit.databinding.FragmentSignUpBinding
import com.example.noteit.ui.MainActivity

class SignUpFragment : Fragment() {

    private var _binding : FragmentSignUpBinding? = null
    val binding get() = _binding!!

    private lateinit var  sessionManager : SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager = SessionManager(requireContext())
        setClicks()
    }

    private fun setClicks() {
        binding.btnSignUp.setOnClickListener {
            sessionManager.setStatus(true)
            sessionManager.setCount(0)
            startActivity(Intent(requireContext(),MainActivity::class.java))
            activity?.finish()
        }
    }

}