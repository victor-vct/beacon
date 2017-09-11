package com.vctapps.beacon.presentation.listbus.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.vctapps.beacon.R
import com.vctapps.beacon.presentation.model.BusModelView


class ListBusItemAdapter(val listBusModelView: MutableList<BusModelView>,
                         val listener: OnClickBus):
        RecyclerView.Adapter<ListBusItemAdapter.BusItemViewHolder>() {

    override fun getItemCount(): Int {
        return listBusModelView.size
    }

    override fun onBindViewHolder(holder: BusItemViewHolder?, position: Int) {
        holder?.bind(listBusModelView[position], listener, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BusItemViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.list_bus_item, parent, false)

        return BusItemViewHolder(view)
    }

    inner class BusItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var busName: TextView = itemView.findViewById(R.id.bust_item_name) as TextView
        var busDestiny: TextView = itemView.findViewById(R.id.bust_item_destiny) as TextView
        var arriveAt: TextView = itemView.findViewById(R.id.bust_item_arrive_at) as TextView
        var star: ImageView = itemView.findViewById(R.id.bust_item_star) as ImageView

        fun bind(bus: BusModelView, listener: OnClickBus, position: Int){
            busName.text = bus.name
            busDestiny.text = bus.destiny
            arriveAt.text = bus.arriveAt

            if(bus.isFavorite) {
                star.visibility = View.VISIBLE
            }else{
                star.visibility = View.GONE
            }

            itemView.setOnClickListener({ listener.onClick(position) })
        }
    }

    interface OnClickBus {
        fun onClick(position: Int)
    }
}