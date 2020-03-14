package com.stamenkovski.mktv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stamenkovski.mktv.models.PlayItem
import com.stamenkovski.mktv.utils.C
import com.stamenkovski.mktv.utils.GlideApp
import com.stamenkovski.mktv.utils.GlideRequests
import kotlinx.android.synthetic.main.playlist_item.view.*

class PlaylistAdapter(var context: Context, var playlistItems: List<PlayItem>): RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {
    var inflater: LayoutInflater = LayoutInflater.from(context)
    public var glideRequest = GlideApp.with(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.playlist_item, parent, false)

        return ViewHolder(view, glideRequest)
    }

    override fun getItemCount(): Int {
        return playlistItems.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(playlistItems[position])
    }

     class ViewHolder(itemView: View, var glideRequest: GlideRequests) : RecyclerView.ViewHolder(itemView) {
            private val textViewChannelName: TextView? = itemView.textView_channel_name
            private val imageViewChannel: ImageView = itemView.imageView_channel

            fun bind(item: PlayItem){
                textViewChannelName?.text = item.name
                glideRequest
                    .load(C.IMAGE.URL + item.image)
                    .centerInside()
                    .into(imageViewChannel)
            }
    }
}