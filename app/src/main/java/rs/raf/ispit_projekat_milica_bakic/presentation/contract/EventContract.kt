package rs.raf.ispit_projekat_milica_bakic.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.ispit_projekat_milica_bakic.data.model.Event
import rs.raf.ispit_projekat_milica_bakic.presentation.states.AddEventState
import rs.raf.ispit_projekat_milica_bakic.presentation.states.CityTimeState
import rs.raf.ispit_projekat_milica_bakic.presentation.states.DeleteEventState
import rs.raf.ispit_projekat_milica_bakic.presentation.states.EventState

class EventContract {

    interface ViewModel {

        val eventsState: LiveData<EventState>
        val addEventDone: LiveData<AddEventState>
        val deleteEventDone: LiveData<DeleteEventState>
        val cityTimeState: LiveData<CityTimeState>

        fun getAllEvents()
        fun addEvent(event: Event)
        fun deleteEvent(event_id: String)

        fun fetchTimeForCity(city : String)
        fun getTimeForCity()

    }

}