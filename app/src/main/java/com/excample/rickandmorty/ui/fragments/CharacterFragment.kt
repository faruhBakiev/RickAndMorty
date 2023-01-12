package com.excample.rickandmorty.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.excample.rickandmorty.databinding.FragmentCharacterBinding
import com.excample.rickandmorty.ui.adapters.CharacterAdapter

class CharacterFragment : Fragment() {

    private var binding: FragmentCharacterBinding? = null
    private val viewModel by viewModels<CharacterViewModel>()
    private val characterAdapter = CharacterAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserve()
        initialize()
    }

    private fun initialize() {
        binding?.rvCharacters?.adapter = characterAdapter
    }

    private fun setupObserve() {
        viewModel.charactersLiveData.observe(viewLifecycleOwner) {
            characterAdapter.submitList(it)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}