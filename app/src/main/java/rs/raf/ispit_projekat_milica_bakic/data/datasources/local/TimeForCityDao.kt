package rs.raf.ispit_projekat_milica_bakic.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.ispit_projekat_milica_bakic.data.model.Event
import rs.raf.ispit_projekat_milica_bakic.data.model.TimeCity

@Dao
abstract class TimeForCityDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: TimeCity): Completable

    @Query("SELECT * FROM city_time")
    abstract fun getCityTime(): Observable<TimeCity>

    @Query("DELETE FROM city_time")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entity: TimeCity) {
        deleteAll()
        insert(entity).blockingAwait()
    }

}