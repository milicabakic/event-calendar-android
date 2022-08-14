package rs.raf.ispit_projekat_milica_bakic.presentation.states

import rs.raf.ispit_projekat_milica_bakic.data.model.Event

sealed class EventState {

    object DataFetched: EventState()
    data class Success(val recipes: List<Event>): EventState()
    data class Error(val message: String): EventState()
}