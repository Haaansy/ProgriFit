package com.cc17.progrifit30.fragments.add

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.cc17.progrifit30.R


class AddFragment : Fragment() {
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        sharedPref = requireActivity().getSharedPreferences("route_name", Context.MODE_PRIVATE)

        view.findViewById<Button>(R.id.setcardio_edit_route_button).setOnClickListener {
            val routeName = view.findViewById<EditText>(R.id.routeName_input).text.toString()
            if(inputCheck(routeName)){
                sharedPref.edit().putString("route_name", routeName).apply()
                view.findNavController().navigate(R.id.action_addFragment_to_mapsFragment)
            } else {
                Toast.makeText(requireContext(), "Invalid Input. Please Try Again.", Toast.LENGTH_LONG).show()
            }
        }
        return view
    }

    // Check Input Function [ CHECK EMPTY FIELDS ]
    private fun inputCheck(name: String): Boolean {
        return !(TextUtils.isEmpty(name))
    }
}
