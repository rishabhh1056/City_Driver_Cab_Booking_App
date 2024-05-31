package com.example.citydriver

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.citydriver.databinding.FragmentAdharBinding


class AdharFragment : Fragment() {

   private lateinit var binding: FragmentAdharBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdharBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        binding.nextBtn.setOnClickListener {
            startActivity(Intent(requireActivity(),CityDriveMainActivity::class.java))
            requireActivity().finish()
        }
        return binding.root
    }


}