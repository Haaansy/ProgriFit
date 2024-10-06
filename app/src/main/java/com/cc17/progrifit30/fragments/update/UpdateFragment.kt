package com.cc17.progrifit30.fragments.update

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cc17.progrifit30.R
import com.cc17.progrifit30.db.model.UserRoutes
import com.cc17.progrifit30.db.userDB.UserRoutesViewModel

class UpdateFragment : Fragment() {

    private val  args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserRoutesViewModel: UserRoutesViewModel
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mUserRoutesViewModel = ViewModelProvider(this).get(UserRoutesViewModel::class.java)
        sharedPref = requireActivity().getSharedPreferences("route_name", Context.MODE_PRIVATE)

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        view.findViewById<EditText>(R.id.update_routeName_input).setText(args.currentUserRoutes.name)

        view.findViewById<Button>(R.id.setcardio_update_delete_button).setOnClickListener {
            deleteItem()
        }

        return view
    }

    private fun deleteItem(){
        val routeName = args.currentUserRoutes.name
        val currentLat = args.currentUserRoutes.currentLat
        val currentLong = args.currentUserRoutes.currentLong
        val destLat = args.currentUserRoutes.destLat
        val destLong = args.currentUserRoutes.destLong

        val userRoutes = UserRoutes(args.currentUserRoutes.id , routeName, currentLat, currentLong, destLat, destLong)

        mUserRoutesViewModel.deleteUserRoutes(userRoutes)

        Toast.makeText(requireContext(), "Successfully Deleted!", Toast.LENGTH_LONG).show()

        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }
}