package com.example.citydriver

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.example.citydriver.databinding.ActivityCityDriveMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.mapbox.android.gestures.MoveGestureDetector
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.ImageHolder
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.expressions.generated.Expression
import com.mapbox.maps.plugin.LocationPuck2D
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.annotation.AnnotationPlugin
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.gestures.OnMoveListener
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import com.mapbox.maps.plugin.gestures.gestures
import com.mapbox.maps.plugin.locationcomponent.location
import java.lang.ref.WeakReference
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorBearingChangedListener
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener

class CityDriveMainActivity : AppCompatActivity() {


    private lateinit var progressBar: ProgressBar
    private val totalTime = 10000L // Total time for countdown in milliseconds (e.g., 10 seconds)
    private val interval = 100L // Interval for countdown updates in milliseconds (e.g., 0.1 second)


    private lateinit var pointAnnotationManager: PointAnnotationManager
    private lateinit var  annotationApi: AnnotationPlugin


    private lateinit var locationPermissionHelper: LocationPermissionHelper
    // private lateinit var mapView:MapboxMap

    private val onIndicatorBearingChangedListener = OnIndicatorBearingChangedListener {
        mapView.mapboxMap.setCamera(CameraOptions.Builder().bearing(it).build())
    }

    private val onIndicatorPositionChangedListener = OnIndicatorPositionChangedListener {
        mapView.mapboxMap.setCamera(CameraOptions.Builder().center(it).build())
        mapView.gestures.focalPoint = mapView.mapboxMap.pixelForCoordinate(it)
    }

    private val onMoveListener = object : OnMoveListener {
        override fun onMoveBegin(detector: MoveGestureDetector) {
            onCameraTrackingDismissed()
        }

        override fun onMove(detector: MoveGestureDetector): Boolean {
            return false
        }

        override fun onMoveEnd(detector: MoveGestureDetector) {}
    }
    private lateinit var mapView: MapView







    private lateinit var binding: ActivityCityDriveMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCityDriveMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.acceptBtn.setOnClickListener {
            startCountdown()
        }


        binding.menuFloatingButton.setOnClickListener {
            binding.drawerLayout.open()
        }

        mapView = binding.mapView

        locationPermissionHelper = LocationPermissionHelper(WeakReference(this))
        locationPermissionHelper.checkPermissions {
            onMapReady()
        }


    }

    private fun startCountdown() {
        val countDownTimer = object : CountDownTimer(totalTime, interval) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = (millisUntilFinished.toFloat() / totalTime * 100).toInt()
                progressBar.progress = progress
            }

            override fun onFinish() {
                progressBar.progress = 0
            }
        }
        countDownTimer.start()
    }





    private fun bitmapFromDrawableRes(context: Context, @DrawableRes resourceId: Int) =
        convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId))

    private fun convertDrawableToBitmap(sourceDrawable: Drawable?): Bitmap? {
        if (sourceDrawable == null) {
            return null
        }
        return if (sourceDrawable is BitmapDrawable) {
            sourceDrawable.bitmap
        } else {
// copying drawable object to not manipulate on the same reference
            val constantState = sourceDrawable.constantState ?: return null
            val drawable = constantState.newDrawable().mutate()
            val bitmap: Bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth, drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        }
    }

    private fun  onMapReady() {
        checkLocationServices()
        mapView.mapboxMap.setCamera(
            CameraOptions.Builder()
                .zoom(14.0)
                .build()
        )

//        val btn1 = findViewById<FloatingActionButton>(R.id.btnforset)
//        val btn2 = findViewById<FloatingActionButton>(R.id.btnforhybrid)

//        btn1.setOnClickListener {
//            loadMapStyle(Style.SATELLITE_STREETS)
//        }
//
//        btn2.setOnClickListener {
//            loadMapStyle(Style.MAPBOX_STREETS)
//        }

        // Load the default map style
        loadMapStyle(Style.STANDARD)
    }

    private fun loadMapStyle(styleUrl: String) {
        mapView.mapboxMap.loadStyle(styleUrl) {
            initLocationComponent()
            setupGesturesListener()
            mapView.mapboxMap.addOnMapClickListener { onMapClick(it)  }
        }
    }

    /*override*/ fun onMapClick(point: Point): Boolean {

        // Add a new marker
        setAddMarker(point)
        return true
    }




    private fun setAddMarker(latLng: Point) {
        Toast.makeText(this, " marker Added ", Toast.LENGTH_SHORT).show()

        bitmapFromDrawableRes(
            this@CityDriveMainActivity,
            R.drawable.my_location
        )?.let {

            annotationApi = mapView.annotations
            pointAnnotationManager = annotationApi.createPointAnnotationManager()
            // Set options for the resulting symbol layer.
            val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
                .withPoint(latLng)
                .withIconImage(it)

            // Add the resulting pointAnnotation to the map.
            pointAnnotationManager.create(pointAnnotationOptions)
        }
    }


    private fun setupGesturesListener() {
        mapView.gestures.addOnMoveListener(onMoveListener)
    }

    private fun initLocationComponent() {
        val locationComponentPlugin = mapView.location
        locationComponentPlugin.updateSettings {
            puckBearing = PuckBearing.COURSE
            puckBearingEnabled = true
            enabled = true
            locationPuck = LocationPuck2D(
                bearingImage = ImageHolder.from(R.drawable.my_location),
                // shadowImage = ImageHolder.from(R.drawable.ic_launcher_background),
                scaleExpression = Expression.interpolate {
                    linear()
                    zoom()
                    stop {
                        literal(0.0)
                        literal(0.6)
                    }
                    stop {
                        literal(20.0)
                        literal(1.0)
                    }
                }.toJson()
            )
        }
        locationComponentPlugin.addOnIndicatorPositionChangedListener(
            onIndicatorPositionChangedListener
        )
        locationComponentPlugin.addOnIndicatorBearingChangedListener(
            onIndicatorBearingChangedListener
        )
    }

    private fun onCameraTrackingDismissed() {
        //Toast.makeText(this, "onCameraTrackingDismissed", Toast.LENGTH_SHORT).show()
        mapView.location
            .removeOnIndicatorPositionChangedListener(onIndicatorPositionChangedListener)
        mapView.location
            .removeOnIndicatorBearingChangedListener(onIndicatorBearingChangedListener)
        mapView.gestures.removeOnMoveListener(onMoveListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.location
            .removeOnIndicatorBearingChangedListener(onIndicatorBearingChangedListener)
        mapView.location
            .removeOnIndicatorPositionChangedListener(onIndicatorPositionChangedListener)
        mapView.gestures.removeOnMoveListener(onMoveListener)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        locationPermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    private fun checkLocationServices() {
        val locationEnabled = isLocationEnabled()
        if (!locationEnabled) {
            showLocationSettingsDialog()
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager =
            getSystemService(LOCATION_SERVICE) as android.location.LocationManager
        return locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)
    }

    private fun showLocationSettingsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Location Services")
        builder.setMessage("Location services are disabled. Do you want to enable them?")
        builder.setPositiveButton("Yes") { _, _ ->
            // Open settings to enable location services
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        builder.setCancelable(false)
        builder.show()
    }



}