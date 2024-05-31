package com.example.citydriver.View.Authentication.Views.Authentication

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
import com.example.citydriver.DriverDetailsMainActivity
import com.example.citydriver.R
import com.example.citydriver.databinding.FragmentFillProfileDetailsBinding


class FillProfileDetailsFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: FragmentFillProfileDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFillProfileDetailsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding.backBtn.setOnClickListener {
         findNavController().navigate(R.id.action_fillProfileDetailsFragment_to_profileOptionsFragment)
        }

        binding.saveBtn.setOnClickListener {
            val name = binding.EtName.text.toString()
             sharedPreferences = context?.getSharedPreferences("name", Context.MODE_PRIVATE)!!
            sharedPreferences.edit()
                .putString("name", name)
                .apply()
            Log.e("TAG", "onCreateView: EtName ${binding.EtName.text.toString()}*** ", )
            startActivity(Intent(requireActivity(), DriverDetailsMainActivity::class.java))
            requireActivity().finish()
        }
        return binding.root


    }


}