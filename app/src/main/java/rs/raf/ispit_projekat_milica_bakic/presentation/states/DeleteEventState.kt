package rs.raf.ispit_projekat_milica_bakic.presentation.states

sealed class DeleteEventState {
    object Success: DeleteEventState()
    data class Error(val message: String): DeleteEventState()
}