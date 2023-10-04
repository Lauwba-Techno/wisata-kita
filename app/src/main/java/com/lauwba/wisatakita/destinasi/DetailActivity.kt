package com.lauwba.wisatakita.destinasi

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.lauwba.wisatakita.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var maps: GoogleMap? = null

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //receive incoming intent
        val fotoDestinasi = intent.getStringExtra("fotoDestinasi")
        val rating = intent.getDoubleExtra("rating", 0.0)
        val title = intent.getStringExtra("namaDestinasi")
        val alamat = intent.getStringExtra("location")
        val deskripsi = intent.getStringExtra("deskripsiDestinasi")
        val lat = intent.getDoubleExtra("lat", 0.0)
        val lng = intent.getDoubleExtra("lng", 0.0)

        //set toolbarnya
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title

        //masukan data ke xml
        binding.rating.text = rating.toString()
        binding.alamat.text = alamat
        binding.deskripsi.text = deskripsi
        Glide.with(binding.root.context)
            .load(fotoDestinasi)
            .into(binding.fotoDestinasi)

        with(binding.mapView) {
            onCreate(savedInstanceState)
            getMapAsync { map ->
                this@DetailActivity.maps = map
                MapsInitializer.initialize(context)
                addMarkerToMaps(LatLng(lat, lng), map)
            }
        }

        this.binding.navigate.setOnClickListener {
            //intent explicit
            try {
                val gmmIntentUri =
                    Uri.parse("google.navigation:q=${lat},${lng}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            } catch (e: ActivityNotFoundException) {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Maps Tidak Terinstall",
                    Snackbar.LENGTH_LONG
                ).setAction("Buka Playstore") {
                    val marketIntent = Intent(Intent.ACTION_VIEW)
                    marketIntent.data = Uri.parse("https://play.google.com")
                    startActivity(Intent.createChooser(marketIntent, "Lanjutkan..."))
                }.show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0, 0, 0, "Type Normal")
        menu?.add(0, 1, 0, "Type Terrain")
        menu?.add(0, 2, 0, "Type Satellite")
        menu?.add(0, 3, 0, "Type Hybrid")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            0 -> this.maps?.mapType = GoogleMap.MAP_TYPE_NORMAL
            1 -> this.maps?.mapType = GoogleMap.MAP_TYPE_TERRAIN
            2 -> this.maps?.mapType = GoogleMap.MAP_TYPE_SATELLITE
            3 -> this.maps?.mapType = GoogleMap.MAP_TYPE_HYBRID
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        this.binding.mapView.onResume()
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        this.binding.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.binding.mapView.onDestroy()
    }

    private fun addMarkerToMaps(
        latLng: LatLng,
        maps: GoogleMap
    ) {
        maps.addMarker(MarkerOptions().position(latLng))?.showInfoWindow()
        maps.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
    }
}