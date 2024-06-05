package com.example.citydriver.View.Authentication.Views.Authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.findNavController
import com.example.citydriver.R
import com.example.citydriver.databinding.FragmentProfileOptionsBinding


class ProfileOptionsFragment : Fragment() {


    private lateinit var binding: FragmentProfileOptionsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileOptionsBinding.inflate(LayoutInflater.from(context))
        // Inflate the layout for this fragment

        binding.carOwner.setOnClickListener {
            findNavController().navigate(R.id.action_profileOptionsFragment_to_fillProfileDetailsFragment)
        }
        binding.bikeOwner.setOnClickListener {
            findNavController().navigate(R.id.action_profileOptionsFragment_to_fillProfileDetailsFragment)
        }
        binding.commercialOwner.setOnClickListener {
            findNavController().navigate(R.id.action_profileOptionsFragment_to_fillProfileDetailsFragment)
        }
        binding.autoOwner.setOnClickListener {
            findNavController().navigate(R.id.action_profileOptionsFragment_to_fillProfileDetailsFragment)
        }

        changeColorStatusBar()
        return binding.root
    }

    fun changeColorStatusBar() {
        val window: Window? = this.activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.statusBarColor = ContextCompat.getColor(this.requireContext(), R.color.lightBlue)
        if (window != null) {
            WindowCompat.getInsetsController(window, window.decorView).apply {
                isAppearanceLightStatusBars = true
            }
        }

    }


}