package rs.raf.ispit_projekat_milica_bakic.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import rs.raf.ispit_projekat_milica_bakic.data.model.Event
import rs.raf.ispit_projekat_milica_bakic.data.model.TimeCity

@Database(
        entities = [Event::class, TimeCity::class],
        version = 1,
        exportSchema = false)
abstract class Database :  RoomDatabase() {

    abstract fun getEventDao(): EventDao
    abstract fun getTimeForCityDao(): TimeForCityDao

}