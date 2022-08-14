package rs.raf.ispit_projekat_milica_bakic.presentation.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rs.raf.ispit_projekat_milica_bakic.databinding.ActivityMainBinding
import rs.raf.ispit_projekat_milica_bakic.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        initListeners()
    }

    private fun initListeners(){
        binding.button1.setOnClickListener {
            val intent = Intent(this,AddEventActivity::class.java)
            startActivity(intent)
        }

        binding.button2.setOnClickListener {
            val intent = Intent(this,ShowEventsActivity::class.java)
            startActivity(intent)
        }
    }


}