package rs.raf.ispit_projekat_milica_bakic.data.datasources.repository

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.ispit_projekat_milica_bakic.data.datasources.local.EventDao
import rs.raf.ispit_projekat_milica_bakic.data.datasources.local.TimeForCityDao
import rs.raf.ispit_projekat_milica_bakic.data.datasources.remote.TimeForCityService
import rs.raf.ispit_projekat_milica_bakic.data.model.Event
import rs.raf.ispit_projekat_milica_bakic.data.model.Resources
import rs.raf.ispit_projekat_milica_bakic.data.model.TimeCity
import timber.log.Timber

class EventRepositoryImpl (
    private val localDataSource: EventDao,
    private val localDataSourceTime: TimeForCityDao,
    private val remoteDataSource: TimeForCityService
) : EventRepository {


    override fun getAll(): Observable<List<Event>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Event(it.event_id, it.name, it.description, it.date, it.time, it.priority, it.url)
                }
            }
    }

    override fun delete(id_event: String): Completable {
        return localDataSource.deleteEvent(id_event)
    }

    override fun insert(event: Event): Completable {
        val eventEntity = Event(event.event_id, event.name, event.description, event.date, event.time, event.priority, event.url)
        return localDataSource
            .insert(eventEntity)
    }

    override fun fetchCityTimeAll(city : String): Observable<Resources<Unit>> {
        return remoteDataSource
            .getTimeForCity(city)
            .doOnNext() {
                Timber.e("Upis u bazu")
                val entities = TimeCity(it.datetime)
                localDataSourceTime.deleteAndInsertAll(entities)
            }
            .map {
                Resources.Success(Unit)
            }
    }

    override fun getCityTime(): Observable<TimeCity> {
        return localDataSourceTime
            .getCityTime()
            .map {
                TimeCity(it.datetime)
            }
    }

}