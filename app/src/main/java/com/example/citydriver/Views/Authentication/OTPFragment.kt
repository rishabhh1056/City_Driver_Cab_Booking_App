package com.example.citydriver.View.Authentication.Views.Authentication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.findNavController
import com.example.citydriver.R
import com.example.citydriver.databinding.FragmentOTPBinding


class OTPFragment : Fragment() {

    private lateinit var binding: FragmentOTPBinding
    private var originalMode : Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentOTPBinding.inflate(layoutInflater)
        originalMode = activity?.window?.attributes?.softInputMode
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        castumazingEntringOTP()

        binding.backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_OTPFragment_to_singinFragment)
        }

        binding.otpVerifyBtn.setOnClickListener {
            findNavController().navigate(R.id.action_OTPFragment_to_profileOptionsFragment)
        }


        binding.otp1.setOnFocusChangeListener { view, hasFocus ->
           activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
        changeColorStatusBar()
        return binding.root
    }



    private fun castumazingEntringOTP() {
        val editText = arrayOf(
            binding.otp1,
            binding.otp2,
            binding.otp3,
            binding.otp4,
            binding.otp5,
            binding.otp6
        )

        for (i in editText.indices) {
            editText[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        if (i < editText.size - 1) {
                            editText[i + 1].requestFocus()
                        } else if (s.length == 0) {
                            if (i > 0) {
                                editText[i - 1].requestFocus()
                            }
                        }
                    } else if (s?.length == 0) {
                        if (i > 0) {
                            editText[i - 1].requestFocus()
                        }
                    }
                }

            })
        }
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

    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = getSystemService(requireContext(), InputMethodManager::class.java)
            if (imm != null) {
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        originalMode?.let { activity?.window?.setSoftInputMode(it) }
    }
}