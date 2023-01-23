package com.utad.ud3_actividad_b_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.utad.ud3_actividad_b_2.databinding.FragmentPlaylistsBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class PlaylistsFragment : Fragment() {
    private var _binding: FragmentPlaylistsBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistsBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Playlists Populares"
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val json = readJsonFromFile("playlists.json")
        val elementList = Gson().fromJson(json, PlaylistsResponse::class.java)

        mainRecyclerView = binding.mainRecyclerView

        val mAdapter = MainAdapterPlaylist(elementList.data) {
            val action = PlaylistsFragmentDirections.actionPlaylistsFragmentToSongsFragment(it)
            findNavController().navigate(action)
        }

        mainRecyclerView.layoutManager = GridLayoutManager(context, 2)
        mainRecyclerView.adapter = mAdapter
    }

    private fun readJsonFromFile(fileName: String): String {
        var json = ""
        val assetManager = requireContext().resources.assets
        try {
            val bufferedReader = BufferedReader(
                InputStreamReader(assetManager.open(fileName))
            )
            val paramsBuilder = StringBuilder()
            var line: String? = bufferedReader.readLine()
            while (line != null) {
                paramsBuilder.append(line)
                line = bufferedReader.readLine()
            }
            json = paramsBuilder.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }

}

