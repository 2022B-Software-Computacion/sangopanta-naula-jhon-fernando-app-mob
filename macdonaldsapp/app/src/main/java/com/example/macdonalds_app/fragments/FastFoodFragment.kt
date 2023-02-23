package com.example.macdonalds_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.macdonalds_app.R
import com.example.macdonalds_app.adapters.ProductAdapter
import com.example.macdonalds_app.providers.HambuguerProvider

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FastFoodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FastFoodFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit  var recyclerView :RecyclerView
    lateinit var recyclerViewPopulares: RecyclerView


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
        val view =  inflater.inflate(R.layout.fragment_fast_food, container, false)

        // Set Recycler view los mas pedidos with its provider
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false) //Set Layaout Manager as Horizontal
        recyclerView.adapter = ProductAdapter(HambuguerProvider.hambuguerList)

        // Set Recycler view Ofertas Irresistibles its provider
        recyclerViewPopulares = view.findViewById(R.id.recyclerViewPopulares)
        recyclerViewPopulares.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewPopulares.adapter = ProductAdapter(HambuguerProvider.hambuguerListPopulares)


        // Set Recycler view Postres with its provider
        recyclerViewPopulares = view.findViewById(R.id.recyclerViewPostres)
        recyclerViewPopulares.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewPopulares.adapter = ProductAdapter(HambuguerProvider.hambuguerListPostres)





        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FastFoodFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FastFoodFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}