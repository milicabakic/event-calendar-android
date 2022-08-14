package rs.raf.ispit_projekat_milica_bakic.presentation.states

import rs.raf.ispit_projekat_milica_bakic.data.model.TimeCity

sealed class CityTimeState {

    object Loading: CityTimeState()
    object DataFetched: CityTimeState()
    data class Success(val timeCity: TimeCity): CityTimeState()
    data class Error(val message: String): CityTimeState()
}