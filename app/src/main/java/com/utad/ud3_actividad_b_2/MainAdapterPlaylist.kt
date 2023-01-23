package com.utad.ud3_actividad_b_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class MainAdapterPlaylist(private val mDataSet: List<Playlist>, var onClick: (Playlist) -> Unit) :
    RecyclerView.Adapter<MainAdapterPlaylist.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.playlist_item_block, parent, false)
        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = mDataSet.get(position)
        holder.bindItems(data)

        holder.itemView.setOnClickListener {
            onClick(data)
        }
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    inner class MainViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        val tittlePlaylist = v.findViewById<TextView>(R.id.tittlePlaylist)
        val followersPlaylist = v.findViewById<TextView>(R.id.followersPlaylist)
        val imagePlaylist = v.findViewById<ImageView>(R.id.imagePlaylist)

        fun bindItems(data: Playlist) {
            tittlePlaylist.text = data.tittle
            followersPlaylist.text = "${formatearNumeroMiles(data.followers)}  followers"
            Picasso.get().load(data.url_photo).into(imagePlaylist)
        }

    }
}

fun formatearNumeroMiles(number: Int): String {
    if (number < 1000) {
        return number.toString()
    }
    val formatter = DecimalFormat("###.###")
    return formatter.format(number)
}
