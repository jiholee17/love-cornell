package com.example.lovecornellandroid

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView

class DraftsLetterAdaptor(private val dataSet: List<Letter>) :

    RecyclerView.Adapter<DraftsLetterAdaptor.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val toText: TextView = view.findViewById(R.id.to_name_text)
        val contentText: TextView = view.findViewById(R.id.content_text)
        val contentBg: TextView = view.findViewById(R.id.content_bg)
        val fromText: TextView = view.findViewById(R.id.from_name_text)
        val button: AppCompatImageButton = view.findViewById(R.id.edit_button)
//        val card: CardView = view.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.drafts_cell_activity, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.toText.text = dataSet[position].receiver
        holder.contentText.text = dataSet[position].content
        holder.fromText.text = dataSet[position].sender
        holder.contentBg.setBackgroundColor(Color.parseColor(dataSet[position].color))


//        var intentLauncher: ActivityResultLauncher<Intent>
//        intentLauncher = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        ) { results ->
//
//        }

        holder.button.setOnClickListener {
            // TODO: switch to edit fragment

        }

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}