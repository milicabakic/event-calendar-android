package rs.raf.ispit_projekat_milica_bakic.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.ispit_projekat_milica_bakic.data.datasources.repository.EventRepository
import rs.raf.ispit_projekat_milica_bakic.data.model.Event
import rs.raf.ispit_projekat_milica_bakic.data.model.Resources
import rs.raf.ispit_projekat_milica_bakic.presentation.contract.EventContract
import rs.raf.ispit_projekat_milica_bakic.presentation.states.AddEventState
import rs.raf.ispit_projekat_milica_bakic.presentation.states.CityTimeState
import rs.raf.ispit_projekat_milica_bakic.presentation.states.DeleteEventState
import rs.raf.ispit_projekat_milica_bakic.presentation.states.EventState
import timber.log.Timber

class AppViewModel (
    private val eventRepository: EventRepository
) : ViewModel(), EventContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val eventsState: MutableLiveData<EventState> = MutableLiveData()
    override val cityTimeState: MutableLiveData<CityTimeState> = MutableLiveData()
    override val addEventDone:  MutableLiveData<AddEventState> = MutableLiveData()
    override val deleteEventDone:  MutableLiveData<DeleteEventState> = MutableLiveData()

    override fun getAllEvents() {
        val subscription = eventRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    eventsState.value = EventState.Success(it)
                },
                {
                    eventsState.value = EventState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun addEvent(event: Event) {
        val subscription = eventRepository
            .insert(event)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addEventDone.value = AddEventState.Success
                },
                {
                    addEventDone.value = AddEventState.Error("Error happened while adding movie")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteEvent(event_id: String) {
        val subscription = eventRepository
            .delete(event_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    deleteEventDone.value = DeleteEventState.Success
                },
                {
                    deleteEventDone.value = DeleteEventState.Error("Error happened while adding movie")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    
    override fun fetchTimeForCity(city : String) {
        val subscription = eventRepository
            .fetchCityTimeAll(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resources.Loading -> cityTimeState.value = CityTimeState.Loading
                        is Resources.Success -> cityTimeState.value = CityTimeState.DataFetched
                        is Resources.Error -> cityTimeState.value = CityTimeState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    cityTimeState.value = CityTimeState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getTimeForCity() {
        val subscription = eventRepository
            .getCityTime()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    cityTimeState.value = CityTimeState.Success(it)
                },
                {
                    cityTimeState.value = CityTimeState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}