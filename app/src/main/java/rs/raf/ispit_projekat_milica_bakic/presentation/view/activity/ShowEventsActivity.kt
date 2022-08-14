package rs.raf.ispit_projekat_milica_bakic.presentation.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.ispit_projekat_milica_bakic.R
import rs.raf.ispit_projekat_milica_bakic.databinding.ActivityShowEventsBinding
import rs.raf.ispit_projekat_milica_bakic.presentation.contract.EventContract
import rs.raf.ispit_projekat_milica_bakic.presentation.states.EventState
import rs.raf.ispit_projekat_milica_bakic.presentation.view.recycler.adapter.EventAdapter
import rs.raf.ispit_projekat_milica_bakic.presentation.view.recycler.diff.EventDiffItemCallback
import rs.raf.ispit_projekat_milica_bakic.presentation.viewmodel.AppViewModel
import rs.raf.ispit_projekat_milica_bakic.presentation.viewmodel.RecyclerEventsViewModel
import timber.log.Timber

class ShowEventsActivity : AppCompatActivity(R.layout.activity_show_events) {

    private lateinit var binding: ActivityShowEventsBinding
    private val mainViewModel: EventContract.ViewModel by viewModel<AppViewModel>()

    private val recyclerEventsViewModel: RecyclerEventsViewModel by viewModels()
    private lateinit var eventAdapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowEventsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        mainViewModel.getAllEvents()
        initUI()
        initObservers()
        initListeners()
    }

    private fun initUI(){
        initRecycler()
    }

    private fun initObservers(){
        recyclerEventsViewModel.getSavedEvents().observe(this, Observer {
            eventAdapter.submitList(it)
        })

        mainViewModel.eventsState.observe(this, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
    }

    private fun initListeners(){

    }

    private fun initRecycler(){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        eventAdapter = EventAdapter(EventDiffItemCallback(), {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "EVENT: " + it.name + " (" + it.description + ") Date:" + it.date + ", Time: " + it.time + ", url: " + it.url)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }, {
            mainViewModel.deleteEvent(it.event_id)
        })
        binding.recyclerView.adapter = eventAdapter
    }

    private fun renderState(state: EventState){
        when (state) {
            is EventState.Success -> {
                eventAdapter.submitList(state.recipes)
            }
            is EventState.Error -> {
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is EventState.DataFetched -> {
                Toast.makeText(this, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
        }
    }

}