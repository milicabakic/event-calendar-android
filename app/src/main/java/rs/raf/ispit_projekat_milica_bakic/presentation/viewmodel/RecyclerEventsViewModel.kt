package rs.raf.ispit_projekat_milica_bakic.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rs.raf.ispit_projekat_milica_bakic.data.model.Event

class RecyclerEventsViewModel : ViewModel()  {

    private val savedEvents : MutableLiveData<List<Event>> = MutableLiveData()
    private val savedEventList : MutableList<Event> = mutableListOf()

    init {
        val listToSubmit = mutableListOf<Event>()
        listToSubmit.addAll(savedEventList)
        savedEvents.value = listToSubmit
    }

    fun getSavedEvents() : LiveData<List<Event>> {
        return savedEvents
    }

}