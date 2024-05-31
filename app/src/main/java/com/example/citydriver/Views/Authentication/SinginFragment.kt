package com.example.citydriver.View.Authentication.Views.Authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.citydriver.R
import com.example.citydriver.databinding.FragmentSinginBinding


class SinginFragment : Fragment() {


    private lateinit var binding: FragmentSinginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSinginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding.nextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_singinFragment_to_OTPFragment)
        }
        return binding.root
    }

}

