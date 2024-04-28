package com.example.yaroslavlquest.bottomnav.map


import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.yaroslavlquest.MainActivity
import com.example.yaroslavlquest.R
import com.example.yaroslavlquest.databinding.FragmentMapBinding
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider


class MapFragment : Fragment() {

    private lateinit var binding: FragmentMapBinding
    private lateinit var mapView: MapView
    private val placemarkTapListener = MapObjectTapListener { _, point ->
        Toast.makeText(
            this@MapFragment.context,
            "Tapped the point (${point.longitude}, ${point.latitude})",
            Toast.LENGTH_SHORT
        ).show()
        true
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        MapKitFactory.setApiKey("d344a14b-86e1-4652-af95-bf2fcee2ced4")
        MapKitFactory.initialize(this.context)
        val v: View = inflater.inflate(R.layout.fragment_map, container, false)
        mapView = v.findViewById<View>(R.id.mapview) as MapView
        val map = mapView.getMap().move(
            CameraPosition(
                Point(57.589939, 39.857024),
                /* zoom = */ 17.0f,
                /* azimuth = */ 150.0f,
                /* tilt = */ 30.0f
            )
        )
        requestLocation()
        var mapKit: MapKit = MapKitFactory.getInstance()
        var locationally = mapKit.createUserLocationLayer(mapView.mapWindow)
        locationally.isVisible = true
        val imageProvider = ImageProvider.fromResource(this.context, R.drawable.placemark_icon)
        val placemark = mapView.map.mapObjects.addPlacemark().apply {
            geometry = Point(57.622459, 39.887001)
            setIcon(imageProvider)
        }
        placemark.addTapListener(placemarkTapListener)

        return v

    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
    }
    override fun onStart() {
       super.onStart()
       MapKitFactory.getInstance().onStart()
       mapView.onStart()
    }
    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
    private fun requestLocation(){

        if(this.context?.let { ActivityCompat.checkSelfPermission(it, android.Manifest.permission.ACCESS_FINE_LOCATION) } != PackageManager.PERMISSION_GRANTED &&
            this.context?.let { ActivityCompat.checkSelfPermission(it, android.Manifest.permission.ACCESS_COARSE_LOCATION) } != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((activity as MainActivity), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), 0)
            return
        }

    }

}
//class MainActivity : AppCompatActivity() {
//    private lateinit var mapView: MapView
//    lateinit var button_id: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//        super.onCreate(savedInstanceState)
//        MapKitFactory.setApiKey("d344a14b-86e1-4652-af95-bf2fcee2ced4")
//        MapKitFactory.initialize(this)
//        setContentView(R.layout.activity_main)
//        mapView = findViewById(R.id.mapview)
//        mapView.getMap().move(
//            CameraPosition(
//                Point(57.589939, 39.857024),
//                /* zoom = */ 17.0f,
//                /* azimuth = */ 150.0f,
//                /* tilt = */ 30.0f
//            )
//        )
//
//        button_id = findViewById(R.id.button_id)
//        requestLocation()
//        var mapKit: MapKit = MapKitFactory.getInstance()
//        var locationmapkit = mapKit.createUserLocationLayer(mapView.mapWindow)
//        locationmapkit.isVisible = true
//        button_id.setOnClickListener{
//
//        }
//    }
//    private fun requestLocation(){
//        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), 0)
//            return
//        }
//
//    }
//    override fun onStart() {
//        super.onStart()
//        MapKitFactory.getInstance().onStart()
//        mapView.onStart()
//    }
//
//    override fun onStop() {
//        mapView.onStop()
//        MapKitFactory.getInstance().onStop()
//        super.onStop()
//    }
//
//}



