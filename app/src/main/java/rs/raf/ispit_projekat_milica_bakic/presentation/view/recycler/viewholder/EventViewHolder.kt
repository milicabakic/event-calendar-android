package rs.raf.ispit_projekat_milica_bakic.presentation.view.recycler.viewholder

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import rs.raf.ispit_projekat_milica_bakic.data.model.Event
import rs.raf.ispit_projekat_milica_bakic.databinding.LayoutEventItemBinding

class EventViewHolder (
        private val itemBinding: LayoutEventItemBinding,
        onItemClicked: (Int) -> Unit, onItemDelete: (Int) -> Unit) : RecyclerView.ViewHolder(itemBinding.root) {

    init {
        itemBinding.eventItem.setOnClickListener {
            onItemClicked.invoke(adapterPosition)
        }

        itemBinding.deleteEvent.setOnClickListener{
            onItemDelete.invoke(adapterPosition)
        }
    }

    fun bind(event : Event) {
        itemBinding.eventName.text = event.name
        itemBinding.eventDescription.text = event.description
        itemBinding.eventDate.text = event.date.toString()
        itemBinding.eventTime.text = event.time
        itemBinding.eventUrl.text = event.url

        if(event.priority.equals("High")) {
            itemBinding.eventItem.setBackgroundColor(Color.parseColor("#F08080"))
        }
        else if(event.priority.equals("Medium")){
            itemBinding.eventItem.setBackgroundColor(Color.parseColor("#90EE90"))
        }

    }
}