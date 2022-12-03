package com.example.lovecornellandroid

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView


class DraftsLetterAdaptor(private val dataSet: ArrayList<Draft>) :

    RecyclerView.Adapter<DraftsLetterAdaptor.ViewHolder>() {

    lateinit var mOnItemClickListener : OnItemClickListener

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val toText: TextView = view.findViewById(R.id.to_name_text)
        val contentText: TextView = view.findViewById(R.id.content_text)
        val contentBg: TextView = view.findViewById(R.id.content_bg)
        val fromText: TextView = view.findViewById(R.id.from_name_text)
        val button: AppCompatImageButton = view.findViewById(R.id.edit_button)
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
        var colorText = dataSet[position].color
        if(colorText[0]!='#'){
            colorText = "#$colorText"
        }
        holder.contentBg.setBackgroundColor(Color.parseColor(colorText))

        holder.button.setOnClickListener {
            // TODO: switch to edit fragment
            if(mOnItemClickListener != null){
                val d = dataSet[position]
                mOnItemClickListener.onItemClick(d.receiver,d.sender,d.id,d.content,d.color)
            }
        }

    }

    interface OnItemClickListener{
        fun onItemClick(to: String, from : String, id : String, content : String, color : String)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        mOnItemClickListener = onItemClickListener
    }


    override fun getItemCount(): Int {
        return dataSet.size
    }
}