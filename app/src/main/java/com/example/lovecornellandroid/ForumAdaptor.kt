package com.example.lovecornellandroid

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ForumAdaptor(private val dataSet: List<Letter>, val token : String) :
    RecyclerView.Adapter<ForumAdaptor.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val toText: TextView = view.findViewById(R.id.to_name_text)
        val contentText: TextView = view.findViewById(R.id.content_text)
        val contentBg: TextView = view.findViewById(R.id.content_bg)
        val timeText: TextView = view.findViewById(R.id.time_text)
        val fromText: TextView = view.findViewById(R.id.from_name_text)
        val saveButton: AppCompatImageButton = view.findViewById(R.id.save_button)
        val shareButton: AppCompatImageButton = view.findViewById(R.id.share_button)
//        val card: CardView = view.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.forum_cell_activity, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.contentBg.setBackgroundColor(Color.parseColor(dataSet[position].color))
        val id = dataSet[position].id
        holder.toText.text = dataSet[position].receiver
        holder.contentText.text = dataSet[position].content
        holder.timeText.text = dataSet[position].timestamp
        holder.fromText.text = dataSet[position].sender
        var colorText = dataSet[position].color
        if(colorText[0]!='#'){
            colorText = "#$colorText"
        }
        holder.contentBg.setBackgroundColor(Color.parseColor(colorText))

        holder.saveButton.setOnClickListener {
            saveLetter(id,token){}
        }

        holder.shareButton.setOnClickListener {
            // TODO: share this letter
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Check out this letter:\n" + holder.contentText.text)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            holder.itemView.context.startActivity(shareIntent)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}