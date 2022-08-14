package rs.raf.ispit_projekat_milica_bakic.presentation.view.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.lifecycle.Observer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.ispit_projekat_milica_bakic.R
import rs.raf.ispit_projekat_milica_bakic.data.model.Event
import rs.raf.ispit_projekat_milica_bakic.databinding.ActivityAddEventBinding
import rs.raf.ispit_projekat_milica_bakic.presentation.contract.EventContract
import rs.raf.ispit_projekat_milica_bakic.presentation.states.AddEventState
import rs.raf.ispit_projekat_milica_bakic.presentation.states.CityTimeState
import rs.raf.ispit_projekat_milica_bakic.presentation.viewmodel.AppViewModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class AddEventActivity : AppCompatActivity(R.layout.activity_add_event) {

    private lateinit var binding: ActivityAddEventBinding
    private val mainViewModel: EventContract.ViewModel by viewModel<AppViewModel>()

    private lateinit var time: String
    private lateinit var date: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        initListeners()
        initObservers()
    }

    private fun initListeners(){
        binding.btnCheckTime.setOnClickListener {
            if(binding.city.text.toString().isNotEmpty()){
                mainViewModel.getTimeForCity()
                mainViewModel.fetchTimeForCity(binding.city.text.toString())
            }
        }

        val adapterAutoComplete = ArrayAdapter(this, android.R.layout.simple_list_item_1, resources.getStringArray(R.array.cities))
        binding.city.setAdapter(adapterAutoComplete)

        var cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, day)

            val myFormat = "dd.MM.yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            binding.btnSetDate.text = sdf.format(cal.time)
            this.date = sdf.format(cal.time)
        }

        binding.btnSetDate.setOnClickListener {
            DatePickerDialog(this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.btnSetTime.setOnClickListener {
            val currentTime = Calendar.getInstance()
            val hours = currentTime[Calendar.HOUR_OF_DAY]
            val minutes = currentTime[Calendar.MINUTE]
            val timePicker: TimePickerDialog = TimePickerDialog(this,
                { timePicker, selectedHour, selectedMinute -> this.time = "$selectedHour:$selectedMinute"; binding.btnSetTime.text = this.time }, hours, minutes, false)
            timePicker.show()
        }

        binding.btnSaveEvent.setOnClickListener {
            if(binding.eventName.text.toString().isNullOrEmpty() || binding.eventDescription.text.toString().isNullOrEmpty() || binding.btnSetDate.text.toString().equals("Set date")
                    || binding.btnSetTime.text.toString().equals("Set time") || binding.eventUrl.text.toString().isNullOrEmpty()){
                Toast.makeText(this, "All values must be entered", Toast.LENGTH_LONG).show()
            }
            else{
                val event_id = UUID.randomUUID().toString()
                val name = binding.eventName.text.toString()
                val desc = binding.eventDescription.text.toString()
                val priority = binding.spinner.selectedItem.toString()
                val url = binding.eventUrl.text.toString()

                mainViewModel.addEvent(Event(event_id, name, desc, this.date, this.time, priority, url))
            }

        }
    }

    private fun initObservers(){

        mainViewModel.cityTimeState.observe(this, Observer {
            Timber.e(it.toString())
            renderState(it)
        })

        mainViewModel.addEventDone.observe(this, Observer {
            Timber.e(it.toString())
            renderStateEvent(it)
        })

    }

    private fun renderState(state: CityTimeState) {
        when (state) {
            is CityTimeState.Success -> {
                binding.btnCheckTime.text = state.timeCity.datetime
                println(state.timeCity.datetime)
            }
            is CityTimeState.Error -> {
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is CityTimeState.DataFetched -> {
                Toast.makeText(this, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun renderStateEvent(state: AddEventState) {
        when (state) {
            is AddEventState.Success -> {
                finish()
            }
            is AddEventState.Error -> {
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}