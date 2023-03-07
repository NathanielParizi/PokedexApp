package com.example.pokedex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.databinding.FragmentProfileBinding
import com.example.pokedex.model.PokeApiResponse
import com.example.pokedex.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var mUser: User

    private lateinit var pokemon: PokeApiResponse
    private val viewModel: PokemonViewModel by lazy {
        ViewModelProvider(requireActivity())[PokemonViewModel::class.java]
    }
    private val binding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setupObserver()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchBtn.setOnClickListener {
            updateUser()
            val pokemonName = binding.pokemonEditText.text.toString()
            viewModel.fetchPokemon(pokemonName)
        }
        binding.buyBtn.setOnClickListener {
            updateUser()
            searchPokemon()
        }
    }

    private fun spendMoney() {
        mUser.balance = mUser.balance.minus(pokemon.baseExperience * 6)
        binding.balanceEditText.setText(mUser.balance.toString())
        updateUser()
    }

    private fun updateUser() {
        mUser.firstName = binding.firstNameEditTxt.text.toString()
        mUser.lastName = binding.lastNameEditTxt.text.toString()
        mUser.email = binding.emailEditText.text.toString()
        mUser.balance = binding.balanceEditText.text.toString().toInt()
        mUser.accountNumber = binding.accountNumberEditText.text.toString().toInt()
        viewModel.updateUser(mUser)
    }

    private fun refreshFields() {
        binding.firstNameEditTxt.setText(mUser.firstName)
        binding.lastNameEditTxt.setText(mUser.lastName)
        binding.accountNumberEditText.setText(mUser.accountNumber.toString())
        binding.emailEditText.setText(mUser.email)
        binding.balanceEditText.setText(mUser.balance.toString())

        binding.pokemonNameTextView.text = "Pokemon Name: ${viewModel.pokemon.value?.name ?: ""}"
        binding.pokemonCost.text = "Cost: ${viewModel.pokemon.value?.baseExperience ?: ""}"
        binding.pokemonAbilityTextView.text =
            "Ability: ${viewModel.pokemon.value?.abilities?.get(0)?.ability?.name ?: ""}"
        binding.pokemonIdTextView.text = "ID: ${viewModel.pokemon.value?.id ?: ""}"
    }

    private fun searchPokemon() {
        if (viewModel.isEnoughMoney()) {
            spendMoney()
            viewModel.playSound(context)
            Toast.makeText(
                context,
                "You bought ${pokemon.name} for ${(pokemon.baseExperience * 6)}",
                LENGTH_LONG
            )
                .show()
        } else {
            Toast.makeText(
                context,
                "Not enough money! Need $${(pokemon.baseExperience * 6)} to buy ${pokemon.name}.",
                LENGTH_LONG
            )
                .show()
        }
    }

    private fun setupObserver() {
        viewModel.pokemon.observe(viewLifecycleOwner) {
            pokemon = it
            refreshFields()

        }
        viewModel.mUser.observe(viewLifecycleOwner) {
            mUser = it
            refreshFields()
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): ProfileFragment = ProfileFragment()
    }
}