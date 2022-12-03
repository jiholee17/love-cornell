package com.example.lovecornellandroid

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"
private const val ARG_PARAM5 = "param5"
private const val ARG_PARAM6 = "param6"

/**
 * A simple [Fragment] subclass.
 * Use the [PostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var from: String? = null
    private var content: String? = null
    private var color: String? = null
    private var id: String? = null

//    private var selectedPos : Int =
    lateinit var toText : EditText
    lateinit var fromText : EditText
    lateinit var contentText : EditText
    lateinit var sendButton: Button
    lateinit var draftButton: Button
    lateinit var deleteButton: ImageButton
    lateinit var myDataset: MutableList<dataColor>
    lateinit var contentBg: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            from = it.getString(ARG_PARAM3)
            content = it.getString(ARG_PARAM4)
            color = it.getString(ARG_PARAM5)
            id = it.getString(ARG_PARAM6)
        }
//        val name = intent.extras?.getString("name")
//        val artist = intent.extras?.getString("artist")
//        val album = intent.extras?.getString("album")
//        val position = intent.extras?.getInt("position")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var screen = inflater.inflate(R.layout.fragment_post, container, false)

        var selPosition = 0

        toText = screen.findViewById<EditText>(R.id.to_name_text)
        fromText = screen.findViewById<EditText>(R.id.from_name_text)
        contentText = screen.findViewById<EditText>(R.id.content_text)
        contentBg = screen.findViewById(R.id.content_bg)
        toText.setText(param2)
        fromText.setText(from)
        contentText.setText(content)
        contentBg.setBackgroundColor(Color.parseColor(color))
        contentText.setBackgroundColor(Color.parseColor(color))


        sendButton = screen.findViewById(R.id.send_button)
        draftButton = screen.findViewById(R.id.draft_button)
        deleteButton = screen.findViewById<ImageButton>(R.id.delete_button)

        var recview = screen.findViewById<RecyclerView>(R.id.color_palate)
        recview.layoutManager = GridLayoutManager(requireContext(), 5)
        myDataset = mutableListOf<dataColor>(
            dataColor("#fff0e1"),
            dataColor("#feadac"),
            dataColor("#ffc7c6"),
            dataColor("#fedbd5"),
            dataColor("#ffb6e1"),
            dataColor("#fdc6e6"),
            dataColor("#ffe0f2"),
            dataColor("#fefbdc"),
            dataColor("#fff7b9"),
            dataColor("#fff09b"),
            dataColor("#ffcea3"),
            dataColor("#ffd7b3"),
            dataColor("#e6f4ff"),
            dataColor("#d3ecff"),
            dataColor("#c5e5fe"),
            dataColor("#afdbfe"),
            dataColor("#dae1fe"),
            dataColor("#e5eafe"),
            dataColor("#ead8fe"),
            dataColor("#f5e9ff"),
            dataColor("#ddc4fd"),
            dataColor("#abfcff"),
            dataColor("#c7fdff"),
            dataColor("#e1ffff"),
            dataColor("#e9fff3"),
            dataColor("#d2ffea"),
            dataColor("#c2ffe0"),
            dataColor("#edfbda"),
            dataColor("#e9f8cf"),
            dataColor("#e4febd")
            )
        for (i in 0..myDataset.size - 1){
            if(myDataset[i].color == color){
                selPosition = i
            }
        }
        var adapter = recViewColor(myDataset,selPosition)
        class child : recViewColor.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("itemClick",position.toString())
                contentBg.setBackgroundColor(Color.parseColor(myDataset[position].color))
                contentText.setBackgroundColor(Color.parseColor(myDataset[position].color))

                selPosition = position
            }
        }
        adapter.setOnItemClickListener(child())

        recview.adapter = adapter

        sendButton.setOnClickListener{
            if(id=="-1"){
                postLetter(toText.text.toString(),fromText.text.toString(),contentText.text.toString(),myDataset[selPosition].color){}
            }else{
                editDraft(toText.text.toString(),fromText.text.toString(),contentText.text.toString(),myDataset[selPosition].color,id!!){}
                postDraft(id!!){}
            }
        }

        draftButton.setOnClickListener{
            if(id=="-1"){
                createDraft(toText.text.toString(),fromText.text.toString(),contentText.text.toString(),myDataset[selPosition].color,param1!!){}
            }else{
                editDraft(toText.text.toString(),fromText.text.toString(),contentText.text.toString(),myDataset[selPosition].color,id!!){}
            }
        }

        deleteButton.setOnClickListener{
            toText.setText("")
            fromText.setText("")
            contentText.setText("")

            //TODO connect with edit
            if(id!="-1"){
                deleteDraft(param1!!,id!!){}
            }
        }

        return screen


    }

    public fun updateContentBackground(position : Int){
        contentText.setBackgroundColor(Color.parseColor(myDataset[position].color))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PostFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String = "", param2: String = "", from:String = "", content: String = "", color: String = "#fff0e1", id: String = "-1") =
            PostFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, from)
                    putString(ARG_PARAM4, content)
                    putString(ARG_PARAM5, color)
                    putString(ARG_PARAM6, id)
                }
            }
    }


}