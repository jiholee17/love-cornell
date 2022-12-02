package com.example.lovecornellandroid

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var screen = inflater.inflate(R.layout.fragment_post, container, false)

        var recview = screen.findViewById<RecyclerView>(R.id.color_palate)
        recview.layoutManager = GridLayoutManager(requireContext(), 4)
        var myDataset = mutableListOf<dataColor>(
            dataColor(Color.rgb(0, 204, 0)),
            dataColor(Color.rgb(0, 204, 102)),
            dataColor(Color.rgb(0, 204, 204)),
            dataColor(Color.rgb(0, 102, 204)),
            dataColor(Color.rgb(0, 0, 204)),
            dataColor(Color.rgb(102, 0, 204)),
            dataColor(Color.rgb(127, 0, 205)),
            dataColor(Color.rgb(204, 0, 204)),
            dataColor(Color.rgb(153, 51, 205)),
            dataColor(Color.rgb(204, 0, 0)),
            dataColor(Color.rgb(255, 0, 0)),
            dataColor(Color.rgb(255, 51, 51)),
            dataColor(Color.rgb(255, 255, 51)),
            dataColor(Color.rgb(51, 255, 51)),
            dataColor(Color.rgb(102, 255, 102)),
            dataColor(Color.rgb(102, 255, 255)),

            )




        recview.adapter = recViewColor(myDataset)
        return screen


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
        fun newInstance(param1: String = "", param2: String = "") =
            PostFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}