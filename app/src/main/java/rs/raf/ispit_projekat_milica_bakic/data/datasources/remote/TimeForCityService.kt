package rs.raf.ispit_projekat_milica_bakic.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import rs.raf.ispit_projekat_milica_bakic.data.model.TimeCity

interface TimeForCityService {


    @GET("timezone/Europe/{city}")
    fun getTimeForCity(@Path("city") city : String): Observable<TimeCity>

}