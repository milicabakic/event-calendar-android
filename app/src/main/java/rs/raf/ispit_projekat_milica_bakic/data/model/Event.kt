package rs.raf.ispit_projekat_milica_bakic.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "events")
data class Event (

   @PrimaryKey
   val event_id : String,
   val name : String,
   val description : String,
   val date : String,
   val time : String,
   val priority : String,
   val url : String

) : Serializable