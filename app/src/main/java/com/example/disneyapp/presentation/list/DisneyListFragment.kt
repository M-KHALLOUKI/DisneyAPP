package com.example.disneyapp.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.disneyapp.R

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

        val disneList = arrayListOf<Disney>().apply {
            add(Disney("Aladdin"))
            add(Disney("Mickey"))
            add(Disney("Donald"))
        }
        adapter.updateList(disneList)
    }
}