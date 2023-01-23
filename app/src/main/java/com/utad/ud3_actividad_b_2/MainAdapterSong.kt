package com.utad.ud3_actividad_b_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MainAdapterSong(
    private val mDataSet: List<Song>, var onClick: (Song) -> Unit
) : RecyclerView.Adapter<MainAdapterSong.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.song_item_block, parent, false)
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
        val tittleSong = v.findViewById<TextView>(R.id.tittleSong)
        val autorSong = v.findViewById<TextView>(R.id.autorSong)
        val imageSong = v.findViewById<ImageView>(R.id.imageSong)
        val btnFavoriteSong = v.findViewById<CheckBox>(R.id.btnFavoriteSong)
        val btnBlockSong = v.findViewById<CheckBox>(R.id.btnBlockSong)
        val btnOptionsSong = v.findViewById<CheckBox>(R.id.btnOptionsSong)

        fun bindItems(data: Song) {
            tittleSong.text = data.tittle
            autorSong.text = data.autor
            Picasso.get().load(data.url_photo).into(imageSong)

            btnFavoriteSong.setOnCheckedChangeListener { checkBox, isChecked ->
                if (btnFavoriteSong.isChecked) Toast.makeText(
                    v.context, "${data.tittle} añadido a favoritos", Toast.LENGTH_SHORT
                ).show()
                else Toast.makeText(
                    v.context, "${data.tittle} eliminado de favoritos", Toast.LENGTH_SHORT
                ).show()

            }
            btnBlockSong.setOnCheckedChangeListener { checkBox, isChecked ->
                Toast.makeText(v.context, "Block c${data.id}", Toast.LENGTH_SHORT).show()
            }
            btnOptionsSong.setOnCheckedChangeListener { checkBox, isChecked ->
                Toast.makeText(v.context, "Options c${data.id}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}



