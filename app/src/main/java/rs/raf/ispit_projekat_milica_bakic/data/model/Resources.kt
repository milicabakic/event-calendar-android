package rs.raf.ispit_projekat_milica_bakic.data.model

sealed class Resources<out T> {

    data class Success<out T>(val data: T) : Resources<T>()
    data class Loading<out T>(val message: String = "") : Resources<T>()
    data class Error<out T>(val error: Throwable = Throwable(), val data: T? = null): Resources<T>()

}