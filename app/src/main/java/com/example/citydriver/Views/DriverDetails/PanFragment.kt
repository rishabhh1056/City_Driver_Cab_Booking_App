package com.example.citydriver

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.citydriver.databinding.FragmentPanBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class PanFragment : Fragment() {
    private lateinit var binding: FragmentPanBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPanBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        binding.nextBtn.setOnClickListener {
            startActivity(Intent(requireActivity(),CityDriveMainActivity::class.java))
            requireActivity().finish()
        }
        return binding.root
    }


}