package com.utad.ud3_actividad_b_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.utad.ud3_actividad_b_2.databinding.FragmentSongsBinding


class SongsFragment : Fragment() {
    val args: SongsFragmentArgs by navArgs()
    private var _binding: FragmentSongsBinding? = null
    private val binding get() = _binding!!

    private lateinit var songsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSongsBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = args.playlist.tittle
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val elementList: Playlist = args.playlist

        songsRecyclerView = binding.songsRecyclerView

        val mAdapter = MainAdapterSong(elementList.songs) {
            // val action = PlaylistFragmentDirections.actionHomeFragmentToViewNoteFragment(it.songs)
            // findNavController().navigate(action)
            Snackbar.make(
                binding.root,
                "${it.id} - ${it.tittle} - ${it.autor}",
                Snackbar.LENGTH_SHORT
            ).show()
        }
        songsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        songsRecyclerView.adapter = mAdapter
    }
}

