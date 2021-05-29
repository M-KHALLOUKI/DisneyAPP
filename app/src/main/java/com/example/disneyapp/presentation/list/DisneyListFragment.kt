package com.example.disneyapp.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.disneyapp.R
import com.example.disneyapp.presentation.api.DisneyApi
import com.example.disneyapp.presentation.api.DisneyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DisneyListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private val adapter = DisneyAdapter(listOf())
    private val layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_disney_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.disney_recyclerview)

        recyclerView.apply {
            layoutManager = this@DisneyListFragment.layoutManager
            adapter = this@DisneyListFragment.adapter
        }

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.disneyapi.dev")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val DisneyApi: DisneyApi= retrofit.create(DisneyApi::class.java)

        DisneyApi.getDisneyList().enqueue(object: Callback<DisneyResponse>{
            override fun onFailure(call: Call<DisneyResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<DisneyResponse>, response: Response<DisneyResponse>) {
                if (response.isSuccessful && response.body() != null){
                    val disneyResponse = response.body()!!
                    adapter.updateList(disneyResponse.data)

                }
            }

        })
    }
}