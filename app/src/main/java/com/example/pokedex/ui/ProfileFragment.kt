package com.example.pokedex.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var selectedPokemon: String
    private val viewModel: PokemonViewModel by lazy {
        ViewModelProvider(requireActivity())[PokemonViewModel::class.java]
    }
    private val binding by lazy { FragmentProfileBinding.inflate(layoutInflater) }

    private lateinit var searchbtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("silvertag", "onCreateView: ALIVE")

        searchbtn = binding.searchBtn
        searchbtn.setOnClickListener {
            if (viewModel.isEnoughMoney()) {
                Toast.makeText(context, "Searching for Pokemon", LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Not enough", LENGTH_LONG).show()
            }
        }



        selectedPokemon = binding.pokemonEditText.text.toString()
        setupObserver()

//        // Inflate the layout for this fragment
//        viewModel.pokemon?.let {
//            // todo populate the UI
//
////            binding.pokemon = "Name:\t ${it.value.name}"
//        } ?: run {
//            AlertDialog.Builder(requireActivity())
//                .setTitle("Error occurred")
//                .setMessage("No Pokemon available")
//                .setNegativeButton("Dismiss") { dialog, _ ->
//                    dialog.dismiss()
//                }
//        }
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    private fun setupObserver() {
        Log.d("GOLDTAG", "OBSERVER MADE")

        viewModel.pokemon.observe(viewLifecycleOwner) {
            Log.d("GOLDTAG", "${it}")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): ProfileFragment = ProfileFragment()
    }
}