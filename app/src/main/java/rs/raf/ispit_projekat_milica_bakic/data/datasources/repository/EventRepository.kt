package rs.raf.ispit_projekat_milica_bakic.data.datasources.repository

import com.bumptech.glide.load.engine.Resource
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.ispit_projekat_milica_bakic.data.model.Event
import rs.raf.ispit_projekat_milica_bakic.data.model.Resources
import rs.raf.ispit_projekat_milica_bakic.data.model.TimeCity

interface EventRepository {

    //EventDao
    fun getAll(): Observable<List<Event>>
    fun delete(id_event : String): Completable
    fun insert(event: Event): Completable

    //TimeForCityDao
    fun fetchCityTimeAll(city: String): Observable<Resources<Unit>>
    fun getCityTime(): Observable<TimeCity>

}