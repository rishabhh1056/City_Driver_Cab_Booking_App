package com.example.citydriver

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.citydriver.databinding.FragmentPermitBinding


class PermitFragment : Fragment() {

private lateinit var binding: FragmentPermitBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPermitBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding.TakePhotoBtn.setOnClickListener {
            startActivity(Intent(requireActivity(),CityDriveMainActivity::class.java))
            requireActivity().finish()
        }
        return binding.root
    }


}