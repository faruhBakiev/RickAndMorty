package com.excample.rickandmorty.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.excample.rickandmorty.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserve()

    }

    private fun setupObserve() = with(binding) {
        viewModel.getSingleCharacter(args.id)
        viewModel.aboutCharactersLiveData.observe(viewLifecycleOwner){
            Glide.with(this!!.ivImage.context)
                .load(it.image)
                .into(ivImage)
            tvGender.text = it.gender
            tvStatus.text = it.status
            tvSpecies.text = it.species
            tvName.text = it.name
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "HelloWorld", Toast.LENGTH_SHORT).show()
        }
    }
}
