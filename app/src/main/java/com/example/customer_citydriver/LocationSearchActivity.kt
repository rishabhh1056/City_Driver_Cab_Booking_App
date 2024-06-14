package com.example.customer_citydriver

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.customer_citydriver.adapter.FatchLocationAdapter
import com.example.customer_citydriver.databinding.ActivityLocationSearchBinding
import com.example.customer_citydriver.model.Favlocation

class LocationSearchActivity : AppCompatActivity() {

    val locations = ArrayList<Favlocation>()
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var binding: ActivityLocationSearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLocationSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        locations.add(Favlocation("Ghaziabad", R.drawable.add_location_alt_24dp_fill0_wght400_grad0_opsz24, "12345"))
        locations.add(Favlocation("Ghaziabad", R.drawable.add_location_alt_24dp_fill0_wght400_grad0_opsz24, "12345"))
        locations.add(Favlocation("Ghaziabad", R.drawable.add_location_alt_24dp_fill0_wght400_grad0_opsz24, "12345"))
        locations.add(Favlocation("Ghaziabad", R.drawable.add_location_alt_24dp_fill0_wght400_grad0_opsz24, "12345"))
        locations.add(Favlocation("Ghaziabad", R.drawable.add_location_alt_24dp_fill0_wght400_grad0_opsz24, "12345"))
        locations.add(Favlocation("Ghaziabad", R.drawable.add_location_alt_24dp_fill0_wght400_grad0_opsz24, "12345"))




        binding.EtPickUpLocation.setOnClickListener {
            val value = binding.EtPickUpLocation.text.toString()
            if (value == "Your Current location") {
                binding.EtPickUpLocation.setText("")
            }
        }

        binding.backBtn.setNavigationOnClickListener {
            val intent  = Intent(this, CityDriveMainActivity::class.java)
            val bundle = Bundle()
            bundle.putBoolean("value", true)
            intent.putExtras(bundle)
            startActivity(intent)
        }


        binding.llLocationList1.setOnClickListener {
            val intent  = Intent(this, CityDriveMainActivity::class.java)
            val bundle = Bundle()
            bundle.putBoolean("value", true)
            intent.putExtras(bundle)

            startActivity(intent)
            finish()
        }
        binding.llLocationList2.setOnClickListener {
            val intent  = Intent(this, CityDriveMainActivity::class.java)
            val bundle = Bundle()
            bundle.putBoolean("value", true)
            intent.putExtras(bundle)

            startActivity(intent)
            finish()
        }
        binding.llLocationList3.setOnClickListener {
            val intent  = Intent(this, CityDriveMainActivity::class.java)
            val bundle = Bundle()
            bundle.putBoolean("value", true)
            intent.putExtras(bundle)

            startActivity(intent)
            finish()
        }

        binding.rvSearchList.adapter = FatchLocationAdapter(locations)


    }


}