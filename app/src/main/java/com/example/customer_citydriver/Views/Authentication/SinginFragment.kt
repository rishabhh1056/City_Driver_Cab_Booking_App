package com.example.customer_citydriver.View.Authentication.Views.Authentication

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.findNavController
import com.example.customer_citydriver.R
import com.example.customer_citydriver.databinding.FragmentSinginBinding
import com.example.customer_citydriver.manager.SharePreferenceManager


class SinginFragment : Fragment() {

    private lateinit var binding: FragmentSinginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSinginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        binding.nextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_singinFragment_to_OTPFragment)
        }
        binding.EtNumber.setOnFocusChangeListener { v, hasFocus ->

        }

        changeColorStatusBar()
        return binding.root
    }


    fun changeColorStatusBar() {
        val window: Window? = this.activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.statusBarColor = ContextCompat.getColor(this.requireContext(), R.color.white)
        if (window != null) {
            WindowCompat.getInsetsController(window, window.decorView).apply {
                isAppearanceLightStatusBars = true
            }
        }
    }

    fun phoneNumberWatcher() {
        binding.EtNumber.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 10) {
                    binding.nextBtn.isEnabled = true
                }
                else{
                    binding.nextBtn.isEnabled = false
                }
                if (s?.length == 0) {
                    binding.nextBtn.isEnabled = false
                }

            }
        })
    }

}

