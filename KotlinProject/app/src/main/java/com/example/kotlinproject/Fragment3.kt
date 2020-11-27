package com.example.kotlinproject

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment3.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment3 : Fragment() {

    private val sharedPrefFile = "kotlinsharedpreference";
    lateinit var sharedPreferences : SharedPreferences;
    var sharedNameValue : String? = "";

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_3, container, false);

        return rootView;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = this.getActivity()!!.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
        sharedNameValue = sharedPreferences.getString("nickname", "No_Name");

        val strNickname = this.getActivity()!!.getString(R.string.nickname);
        val strFinalNickname = String.format(strNickname, sharedNameValue);

        var textNickname = requireView().findViewById<TextView>(R.id.nickname_welcome);
        textNickname.text = strFinalNickname;
    }
}