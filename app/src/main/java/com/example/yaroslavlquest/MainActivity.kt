package com.example.yaroslavlquest

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.yaroslavlquest.bottomnav.collection.CollectionFragment
import com.example.yaroslavlquest.bottomnav.map.MapFragment
import com.example.yaroslavlquest.bottomnav.profile.ProfileFragment
import com.example.yaroslavlquest.bottomnav.routes.RoutesFragment
import com.example.yaroslavlquest.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.yandex.mapkit.mapview.MapView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (FirebaseAuth.getInstance().currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.bottomNav.selectedItemId = R.id.profile
        val fragmentMap: MutableMap<Int, Fragment> = HashMap()
        fragmentMap[R.id.map] = MapFragment()
        fragmentMap[R.id.routes] = RoutesFragment()
        fragmentMap[R.id.collection] = CollectionFragment()
        fragmentMap[R.id.profile] = ProfileFragment()


//        val fragmentMap = mapOf(
//            R.id.map to MapFragment(),
//            R.id.routes to RoutesFragment(),
//            R.id.collection to CollectionFragment(),
//            R.id.profile to ProfileFragment()
//        )
        binding.bottomNav.setOnItemSelectedListener { item ->
            val fragment = fragmentMap[item.itemId]
            supportFragmentManager.beginTransaction().replace(binding.fragmentContainer.id, fragment!!).commit()
            return@setOnItemSelectedListener true
        }
    }
}