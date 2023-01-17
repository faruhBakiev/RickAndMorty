package com.excample.rickandmorty.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.excample.rickandmorty.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()

    }

    private fun initialize() = with(binding) {
        viewModel.getSingleCharacter(args.id)
        viewModel.aboutCharactersLiveData.observe(viewLifecycleOwner){
            Glide.with(ivImage.context)
                .load(it.image)
                .into(ivImage)
            tvName.text = it.name
            tvGender.text = it.gender
            tvSpecies.text = it.species
            tvStatus.text = it.status
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner){

        }

    }

}
