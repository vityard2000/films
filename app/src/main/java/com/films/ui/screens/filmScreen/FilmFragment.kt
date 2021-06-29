package com.films.ui.screens.filmScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.films.R
import com.films.domain.entities.Film


class FilmFragment : Fragment() {
    private lateinit var viewModel: FilmViewModel

    companion object {
        const val KEY_FILM = "film"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {bundle ->
            viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return FilmViewModel(bundle.getSerializable(KEY_FILM) as Film) as T
                }
            }).get(FilmViewModel::class.java)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.film_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {bundle ->
            //film_fragment__tv.text = (bundle.getSerializable(KEY_FILM) as Film).displayTitle
        }
    }
}