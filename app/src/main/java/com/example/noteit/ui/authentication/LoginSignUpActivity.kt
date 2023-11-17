package com.example.noteit.ui.authentication


import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.noteit.R
import com.example.noteit.core.base.BaseActivity
import com.example.noteit.databinding.ActivityLoginSignUpBinding
import com.example.noteit.databinding.FragmentTextBinding

class LoginSignUpActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginSignUpBinding
    var signUpFragment = SignUpFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(signUpFragment)
    }
    private fun setFragment(frag : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flAuth,frag)
            commit()
        }
    }
    private fun setClicks() {
        binding.apply {
//            btnFirstFragment.setOnClickListener {setFragment(firstFragment)}
//            btnSecondFragment.setOnClickListener {setFragment(secondFragment)}
        }
    }
}