package rs.raf.ispit_projekat_milica_bakic.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.ispit_projekat_milica_bakic.data.model.Event

class EventDiffItemCallback : DiffUtil.ItemCallback<Event>() {

    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.event_id == newItem.event_id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.description == newItem.description &&
                oldItem.url == newItem.url &&
                oldItem.date == newItem.date &&
                oldItem.time == oldItem.time
    }
}