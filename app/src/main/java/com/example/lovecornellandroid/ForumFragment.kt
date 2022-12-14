package com.example.lovecornellandroid

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ForumFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ForumFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_forum, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.forum_recyclerview)

        // TODO: get real dataset from DB
        var letters = ArrayList<Letter>()
//        letterList.add(Letter("receiver","sender","Just some sentence to fill up the space. Hello. Just some sentence to fill up the space. Hello. Just some sentence to fill up the space. Hello. Just some sentence to fill up the space. Hello. Just some sentence to fill up the space. Hello. Just some sentence to fill up the space. Hello. Just some sentence to fill up the space. Hello.","#fefbdc","12/12/12"))
//        letterList.add(Letter("receiver2","sender2","HIIIII","#ffd7b3","22/22/22"))

        getAllLetters{
            letters = it as ArrayList<Letter>
//            Log.d("error",letters.toString())
//            Log.d("error",param1.toString())
            val adapter = ForumAdaptor(letters,param1.toString())
            requireActivity().runOnUiThread {
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this.context)
            }
        }

//        val adapter = ForumAdaptor(letters,param1!!)
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this.context)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ForumFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String = "", param2: String = "") =
            ForumFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}