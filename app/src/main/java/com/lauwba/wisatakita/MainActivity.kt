package com.lauwba.wisatakita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.lauwba.wisatakita.databinding.ActivityMainBinding
import com.lauwba.wisatakita.destinasi.DestinasiFragment
import com.lauwba.wisatakita.galeri.GaleriFragment
import com.lauwba.wisatakita.portal.PortalFragment
import com.lauwba.wisatakita.tentang.TentangFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initial fragment
        this.changeFragment(DestinasiFragment())

        //memberikan on selected listener ke bottom navigation
        binding.bottomMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.portal -> {
                    this.changeFragment(PortalFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.destinasi -> {
                    this.changeFragment(DestinasiFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.galeri -> {
                    this.changeFragment(GaleriFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.tentang -> {
                    this.changeFragment(TentangFragment())
                    return@setOnItemSelectedListener true
                }

            }
            return@setOnItemSelectedListener false
        }
    }

    private fun changeFragment(
        fragment: Fragment
    ) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}