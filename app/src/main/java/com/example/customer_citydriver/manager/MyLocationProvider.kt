import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import com.mapbox.maps.plugin.locationcomponent.LocationConsumer
import com.mapbox.maps.plugin.locationcomponent.LocationProvider

class MyLocationProvider(private val context: Context) : LocationProvider {

    @SuppressLint("MissingPermission")
    fun getLocation(): Location? {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            ?: locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
    }

    override fun registerLocationConsumer(locationConsumer: LocationConsumer) {
        TODO("Not yet implemented")
    }

    override fun unRegisterLocationConsumer(locationConsumer: LocationConsumer) {
        TODO("Not yet implemented")
    }

}
