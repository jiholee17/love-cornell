package com.example.lovecornellandroid

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class SavedLetterAdaptor(private val dataSet: List<Letter>, val token : String) :
    RecyclerView.Adapter<SavedLetterAdaptor.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val toText: TextView = view.findViewById(R.id.to_name_text)
        val contentText: TextView = view.findViewById(R.id.content_text)
        val contentBg: TextView = view.findViewById(R.id.content_bg)
        val timeText: TextView = view.findViewById(R.id.time_text)
        val fromText: TextView = view.findViewById(R.id.from_name_text)
        val button: AppCompatImageButton = view.findViewById(R.id.save_button)
//        val card: CardView = view.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.saved_cell_activity, parent, false)
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
        var saved = true
        holder.contentBg.setBackgroundColor(Color.parseColor(colorText))

        holder.button.setOnClickListener {
            saved = !saved
            Log.d("saved?",saved.toString())
            if(saved){
                saveLetter(id,token){}
            }else{
                unsaveLetter(id,token){}
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}