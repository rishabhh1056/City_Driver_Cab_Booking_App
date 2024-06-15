package com.example.customer_citydriver

import com.mapbox.search.autofill.Query
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.customer_citydriver.Views.HomeScreen.LocationViewModel
import com.example.customer_citydriver.adapter.FatchLocationAdapter
import com.example.customer_citydriver.databinding.ActivityLocationSearchBinding
import com.example.customer_citydriver.retrofit.retrofitClient
import com.mapbox.search.autofill.AddressAutofill
import com.mapbox.search.autofill.AddressAutofillOptions
import com.mapbox.search.autofill.AddressAutofillSuggestion
import com.mapbox.search.ui.adapter.autofill.AddressAutofillUiAdapter
import com.mapbox.search.ui.view.SearchResultsView
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationSearchActivity : AppCompatActivity() {


    companion object {
        const val REQUEST_LOCATION_PERMISSION = 1
    }
    private lateinit var adapter: FatchLocationAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private val locationViewModel : LocationViewModel by viewModels()


    private lateinit var addressAutofill: AddressAutofill

    private lateinit var searchResultsView: SearchResultsView
    private lateinit var addressAutofillUiAdapter: AddressAutofillUiAdapter

    private var ignoreNextMapIdleEvent: Boolean = false
    private var ignoreNextQueryTextUpdate: Boolean = false



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


       /* addressAutofill = AddressAutofill.create()


        lifecycleScope.launchWhenCreated {


            val query = Query.create(binding.EtPickUpLocation.text.toString()) ?: return@launchWhenCreated

            val response = addressAutofill.suggestions(
                query = query,
                options = AddressAutofillOptions()
            )

            if (response.isValue) {
                val suggestions = requireNotNull(response.value)
                Log.e("SearchApiExample", "Autofill suggestions: $suggestions")

                if (suggestions.isNotEmpty()) {
                    // Supposing that a user has selected (clicked in UI) the first suggestion
                    val selectedSuggestion = suggestions.first()

                    Log.e("SearchApiExample", "Selecting first suggestion...")

                    val selectionResponse = addressAutofill.select(selectedSuggestion)
                    selectionResponse.onValue { result ->
                        Log.e("SearchApiExample", "Autofill result: $result")
                    }.onError { e ->
                        Log.e("SearchApiExample", "An error occurred during selection", e)
                    }
                }
            }
            else {
                Log.e("SearchApiExample", "Autofill suggestions error", response.error)
            }
        }*/

        addressAutofill = AddressAutofill.create()

        binding.EtPickUpLocation.addTextChangedListener{editable ->

            val query = Query.create(editable.toString()) ?: return@addTextChangedListener

            lifecycleScope.launch{
                val response = addressAutofill.suggestions(
                    query = query,
                    options = AddressAutofillOptions()
                )
                if (response.isValue) {
                    val suggestions = requireNotNull(response.value)
                    Log.e("SearchApiExample", "Autofill suggestions: $suggestions")

                    if (suggestions.isNotEmpty()) {
                        // Supposing that a user has selected (clicked in UI) the first suggestion
                        val selectedSuggestion = suggestions.first()

                        Log.e("SearchApiExample", "Selecting first suggestion...")

                        val selectionResponse = addressAutofill.select(selectedSuggestion)

                        selectionResponse.onValue { result ->
                            Log.e("SearchApiExample", "Autofill result: $result")
                        }.onError { e ->
                            Log.e("SearchApiExample", "An error occurred during selection", e)
                        }
                    }
                    else{
                        binding.EtPickUpLocation.setText("Your Current location")
                    }
                }
                else {
                    Log.e("SearchApiExample", "Autofill suggestions error", response.error)
                }
            }
        }



       /* binding.EtPickUpLocation.addTextChangedListener(object : TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                if(ignoreNextQueryTextUpdate)
                {
                    ignoreNextQueryTextUpdate = false
                    return
                }
                val query = Query.create(s.toString()) ?: return

                lifecycleScope.launchWhenCreated {

                    val response = addressAutofillUiAdapter.search(query)

                    searchResultsView.isVisible = true
                }



            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }

        })*/





        addressAutofillUiAdapter = AddressAutofillUiAdapter(binding.searchResultsView, addressAutofill)

        addressAutofillUiAdapter.addSearchListener(object : AddressAutofillUiAdapter.SearchListener {
            override fun onSuggestionSelected(suggestion: AddressAutofillSuggestion) {
                updateUIWithSuggestion(suggestion)
            }

            override fun onSuggestionsShown(suggestions: List<AddressAutofillSuggestion>) {
                // Optionally handle showing suggestions in the UI if needed
                Log.e("TAG", "onSuggestionsShown: $suggestions", )
            }

            override fun onError(e: Exception) {
                // Handle error if necessary
                Log.e("TAG", "onError: $e", )
            }
        })






























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



//            val intent  = Intent(this, CityDriveMainActivity::class.java)
//            val bundle = Bundle()
//            bundle.putBoolean("value", true)
//            intent.putExtras(bundle)
//            startActivity(intent)
//            finish()


        }

    private fun updateUIWithSuggestion(suggestion: AddressAutofillSuggestion) {
        // Implement this method to update your UI with the selected suggestion
        // Example: Update EditText fields with suggestion details
        binding.EtPickUpLocation.setText(suggestion.formattedAddress)
        binding.EtPickUpLocation.clearFocus()
        searchResultsView.isVisible = false // Hide search results view
    }



    }




  /*  private fun searchLocation(){

        val retrofit = retrofitClient.retrofit

        retrofit.searchLocation("Ghazipur").enqueue(object : Callback<requestSuggestion>{
            override fun onResponse(call: Call<requestSuggestion>, response: Response<requestSuggestion>) {

                if (response.isSuccessful)
                {
                    val data = response.body()?.suggestions
                    Log.e("TAG", "onResponse: $data ", )
                }
                else
                {
                    Log.e("TAG", "onResponse: else part ", )
                }
            }

            override fun onFailure(call: Call<requestSuggestion>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}", )
            }

        })
    }*/

//    fun getLocation(): android.location.Location? {
//
//        val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        )
//
//
//            return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
//
//
//        return null
//    }








