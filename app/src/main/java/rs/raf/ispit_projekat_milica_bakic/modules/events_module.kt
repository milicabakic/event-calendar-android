package rs.raf.ispit_projekat_milica_bakic.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.ispit_projekat_milica_bakic.data.datasources.local.Database
import rs.raf.ispit_projekat_milica_bakic.data.datasources.remote.TimeForCityService
import rs.raf.ispit_projekat_milica_bakic.data.datasources.repository.EventRepository
import rs.raf.ispit_projekat_milica_bakic.data.datasources.repository.EventRepositoryImpl
import rs.raf.ispit_projekat_milica_bakic.presentation.viewmodel.AppViewModel


val eventModule = module {

    viewModel { AppViewModel(eventRepository = get()) }

    single<EventRepository> { EventRepositoryImpl(localDataSource = get(), localDataSourceTime = get(), remoteDataSource = get()) }

    single { get<Database>().getEventDao() }
    single { get<Database>().getTimeForCityDao() }
    single<TimeForCityService> { create(retrofit = get()) }

}

