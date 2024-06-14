package com.example.customer_citydriver.View.Authentication.Views.Authentication

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.example.customer_citydriver.CityDriveMainActivity
import com.example.customer_citydriver.R
import com.example.customer_citydriver.databinding.FragmentOTPBinding


class OTPFragment : Fragment() {


//    val dialogfromOTPFragment = Dialog(requireContext())

    private lateinit var dialogfromOTPFragment : Dialog
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

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        binding.backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_OTPFragment_to_singinFragment)
        }

        binding.otpVerifyBtn.setOnClickListener {
            showDialog()
            startActivity(Intent(requireContext(), CityDriveMainActivity::class.java))
            requireActivity().finish()
        }


        binding.otp1.setOnFocusChangeListener { view, hasFocus ->
           activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
        changeColorStatusBar()
        return binding.root
    }

    private fun showDialog() {
        dialogfromOTPFragment = Dialog(requireContext())
        dialogfromOTPFragment.setContentView(R.layout.signin_dialog)
        dialogfromOTPFragment.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialogfromOTPFragment.setCancelable(false)
        dialogfromOTPFragment.show()
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



    override fun onDestroy() {
        super.onDestroy()
        originalMode?.let { activity?.window?.setSoftInputMode(it) }
    }
}