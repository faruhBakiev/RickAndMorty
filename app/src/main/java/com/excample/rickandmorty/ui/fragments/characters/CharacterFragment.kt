package com.excample.rickandmorty.ui.fragments.characters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.excample.rickandmorty.data.models.Characters
import com.excample.rickandmorty.databinding.FragmentCharacterBinding
import com.excample.rickandmorty.ui.adapters.CharacterAdapter

class CharacterFragment : Fragment() {

    private var binding: FragmentCharacterBinding? = null
    private val viewModel by viewModels<CharacterViewModel>()
    private val characterAdapter = CharacterAdapter(this::  onClickFirstListener)
    private val allCharacters = arrayListOf<Characters>()
    private var add = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        setupObserve()
    }

    private fun initialize() {
        binding?.rvCharacters?.adapter = characterAdapter
    }

    private fun onClickFirstListener(id: Int) {
        findNavController().navigate(
           CharacterFragmentDirections.actionCharacterFragmentToDetailFragment(
                id
            )
        )
    }

    private fun setupListeners() {
        binding?.btnGetMore?.setOnClickListener {
            viewModel.getChatacters(page = add++)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupObserve() {
        viewModel.charactersLiveData.observe(viewLifecycleOwner) {
            allCharacters.addAll(it)
            characterAdapter.submitList(allCharacters)
            characterAdapter.notifyDataSetChanged()
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "HelloWorld", Toast.LENGTH_SHORT).show()
        }
    }
}