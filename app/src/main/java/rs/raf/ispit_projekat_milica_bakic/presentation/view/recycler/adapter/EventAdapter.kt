package rs.raf.ispit_projekat_milica_bakic.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.ispit_projekat_milica_bakic.data.model.Event
import rs.raf.ispit_projekat_milica_bakic.databinding.LayoutEventItemBinding
import rs.raf.ispit_projekat_milica_bakic.presentation.view.recycler.diff.EventDiffItemCallback
import rs.raf.ispit_projekat_milica_bakic.presentation.view.recycler.viewholder.EventViewHolder

class EventAdapter (
        eventDiffItemCallback: EventDiffItemCallback,
        private val onEventClicked: (Event) -> Unit, private val onEventDelete: (Event) -> Unit) : ListAdapter<Event, EventViewHolder>(eventDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemBinding = LayoutEventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(itemBinding, {
            val event = getItem(it)
            onEventClicked.invoke(event)
        },{
            val event = getItem(it)
            onEventDelete.invoke(event)
        })
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}