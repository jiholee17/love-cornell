package com.example.lovecornellandroid

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.recyclerview.widget.RecyclerView
import org.intellij.lang.annotations.JdkConstants.TitledBorderTitlePosition

class recViewColor(private val dataSet: List<dataColor>, val inputSelPosition : Int) : RecyclerView.Adapter<recViewColor.ViewHolder>()  {

   lateinit var mOnItemClickListener : OnItemClickListener

    var selPosition = inputSelPosition
    var temp = -1

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val color_cube: ImageButton = view.findViewById(R.id.imageButton)
        val color_bg_view: ImageView = view.findViewById(R.id.imageView3)


    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.color_cube, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.color_cube.setBackgroundColor(Color.parseColor(dataSet[position].color))
        viewHolder.itemView.isSelected = position == selPosition

        if(viewHolder.itemView.isSelected){
            viewHolder.color_bg_view.background = viewHolder.itemView.context.resources.getDrawable(R.drawable.border_bg)
        }else{
            viewHolder.color_bg_view.background = null
        }


        viewHolder.color_cube.setOnClickListener{

            if(mOnItemClickListener != null){
                mOnItemClickListener.onItemClick(position)
            }
            viewHolder.itemView.isSelected = true
            temp = selPosition
            selPosition = position
            viewHolder.color_bg_view.background = viewHolder.itemView.context.resources.getDrawable(R.drawable.border_bg)
            notifyItemChanged(temp)
//            val c = it.context as PostFragment
//            c.updateContentBackground(position)

        }

    }


    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        mOnItemClickListener = onItemClickListener
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}