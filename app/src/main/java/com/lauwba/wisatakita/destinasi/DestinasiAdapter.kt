package com.lauwba.wisatakita.destinasi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lauwba.wisatakita.databinding.ItemDestinationBinding

class DestinasiAdapter(
    private val listDestinasi: List<Destinasi>,
    private var onClickListener: (Destinasi) -> Unit
) :
    RecyclerView.Adapter<DestinasiAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemDestinationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listDestinasi.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindItem(listDestinasi[position])
    }

    inner class ViewHolder(val itemDestinationBinding: ItemDestinationBinding) :
        RecyclerView.ViewHolder(itemDestinationBinding.root) {
        fun onBindItem(destinasi: Destinasi) {
            itemDestinationBinding.rating.text = destinasi.rating.toString()
            itemDestinationBinding.namaDestinasi.text = destinasi.namaDestinasi
            itemDestinationBinding.location.text = destinasi.location
            Glide.with(itemDestinationBinding.root.context)
                .load(destinasi.fotoDestinasi)
                .into(itemDestinationBinding.destinasiImageItem)

            //kasih klik ke icon map
            itemDestinationBinding.viewOnDetail.setOnClickListener {
                onClickListener(destinasi)
            }
            itemDestinationBinding.destinasiImageItem.setOnClickListener {
                onClickListener(destinasi)
            }
        }
    }


}