package rs.raf.ispit_projekat_milica_bakic.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.ispit_projekat_milica_bakic.data.model.Event

@Dao
abstract class EventDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: Event): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<Event>): Completable

    @Query("SELECT * FROM events")
    abstract fun getAll(): Observable<List<Event>>

    @Query("DELETE FROM events")
    abstract fun deleteAll()

    @Query("DELETE FROM events WHERE event_id LIKE :id")
    abstract fun deleteEvent(id: String): Completable

    @Transaction
    open fun deleteAndInsertAll(entities: List<Event>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

}