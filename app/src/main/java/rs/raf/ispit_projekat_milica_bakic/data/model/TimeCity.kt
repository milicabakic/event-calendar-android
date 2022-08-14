package rs.raf.ispit_projekat_milica_bakic.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_time")
data class TimeCity (

   @PrimaryKey
   val datetime : String

)