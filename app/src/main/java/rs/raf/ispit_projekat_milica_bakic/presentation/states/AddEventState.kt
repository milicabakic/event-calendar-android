package rs.raf.ispit_projekat_milica_bakic.presentation.states

sealed class AddEventState {
    object Success: AddEventState()
    data class Error(val message: String): AddEventState()
}