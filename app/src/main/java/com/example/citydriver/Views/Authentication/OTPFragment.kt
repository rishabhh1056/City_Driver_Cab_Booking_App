package com.example.citydriver.View.Authentication.Views.Authentication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.citydriver.R
import com.example.citydriver.databinding.FragmentOTPBinding


class OTPFragment : Fragment() {

    private lateinit var binding: FragmentOTPBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOTPBinding.inflate(layoutInflater)

        castumazingEntringOTP()

        binding.backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_OTPFragment_to_singinFragment)
        }

        binding.otpVerifyBtn.setOnClickListener {
            findNavController().navigate(R.id.action_OTPFragment_to_profileOptionsFragment)
        }
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
}