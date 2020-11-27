package com.example.kotlinproject

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment() {

    var recyclerView: RecyclerView? = null
    var recyclerViewAdapter: ShowAdapter? = null
    private lateinit var detailsJSON: JSONObject
    private val sharedPrefFile = "kotlinsharedpreference";
    lateinit var sharedPreferences : SharedPreferences;

    var client = OkHttpClient();
    var request = OKHttpRequest(client);

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_1, container, false)

        sharedPreferences = this.getActivity()!!.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);

        recyclerViewAdapter = ShowAdapter(
            this.activity,
            context
            /*object : OnItemClickListenerList<Show> {
                override fun onItemClick(item: Show) {
                    itemClick()
                }
            }*/
        )

        recyclerView = rootView.findViewById<RecyclerView>(R.id.RiotInfo)
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        recyclerView!!.adapter = recyclerViewAdapter

        getData(recyclerViewAdapter!!);

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // view.findViewById<Button>(R.id.button_second).setOnClickListener {
        //     findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        // }
    }

    fun getData(adapter: ShowAdapter){
        val url = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/ElToredo?api_key=RGAPI-a57d0906-1de2-438c-9c38-10f1355cce82";

        request.GET(url, object : Callback {
            override fun onResponse(call: Call?, response: Response) {
                val responseData = response.body()?.string();
                getActivity()!!.runOnUiThread(Runnable {
                    try {
                        var json = JSONObject(responseData)
                        println("Request Successful!!")
                        println(json)
                        val listdata = ArrayList<String>();
                        val keys = json.keys()
                        while (keys.hasNext()) {
                            val key = keys.next()
                            val value = json.optString(key)
                            listdata.add(value);
                        }
                        adapter.setData(listdata);
                        detailsJSON = json
                        this@Fragment1.fetchComplete()

                        val editor:SharedPreferences.Editor = sharedPreferences.edit();
                        editor.putString("nickname", json["name"] as String?);
                        editor.apply()
                        editor.commit()

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                })
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Request Failure.")
            }
        })

    }


    fun itemClick() {

    }

    fun fetchComplete() {
        println("fetchCOmplete:   " + detailsJSON)
    }
}