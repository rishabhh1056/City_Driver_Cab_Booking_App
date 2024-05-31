package com.example.citydriver

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.citydriver.View.Authentication.Views.Authentication.FillProfileDetailsFragment
import com.example.citydriver.databinding.FragmentDriverDetailBinding


class DriverDetailFragment : Fragment() {

    private lateinit var binding: FragmentDriverDetailBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDriverDetailBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        sharedPreferences = context?.getSharedPreferences("name", Context.MODE_PRIVATE)!!
          val name = sharedPreferences.getString("name", "name")
       
        Log.e("TAG", "onCreateView: $name ", )

        binding.driverName.text = name

        binding.DL.setOnClickListener {
            findNavController().navigate(R.id.action_driverDetailFragment_to_licenceFragment)
        }
        binding.adhar.setOnClickListener {
            findNavController().navigate(R.id.action_driverDetailFragment_to_adharFragment)
        }
        binding.RC.setOnClickListener {
            findNavController().navigate(R.id.action_driverDetailFragment_to_rcFragment)
        }
        binding.panCad.setOnClickListener {
            findNavController().navigate(R.id.action_driverDetailFragment_to_panFragment)
        }
        binding.insurance.setOnClickListener {
            findNavController().navigate(R.id.action_driverDetailFragment_to_insuranceFragment)
        }
        binding.permit.setOnClickListener {
            findNavController().navigate(R.id.action_driverDetailFragment_to_permitFragment)
        }



        return binding.root
    }


}