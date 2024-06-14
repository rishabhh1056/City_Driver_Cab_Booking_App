package com.example.customer_citydriver

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.customer_citydriver.databinding.ActivityPaymentMethodBinding

class PaymentMethodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentMethodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPaymentMethodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        binding.toolbar2.setNavigationOnClickListener {
            val intent  = Intent(this, CityDriveMainActivity::class.java)
            val bundle = Bundle()
            bundle.putBoolean("value", true)
            intent.putExtras(bundle)
            startActivity(intent)
            finish()
        }

        binding.nextBtn.setOnClickListener {
            val intent  = Intent(this, CityDriveMainActivity::class.java)
            val bundle = Bundle()
            bundle.putBoolean("loder", true)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }





}