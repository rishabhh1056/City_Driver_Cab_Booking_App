package com.example.citydriver

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.citydriver.databinding.ActivityCityDriveMainBinding

class CityDriveMainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityCityDriveMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCityDriveMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

     binding.FindDriverBtn.setOnClickListener {
         val intent = Intent(this, PaymentMethodActivity::class.java)
         startActivity(intent)
     }


    }



}