package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeDetailFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()
    private val shoeData = Shoe("", 0.0, "", "")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        binding.shoeViewModel = viewModel
        binding.lifecycleOwner = this
        binding.shoeData = shoeData

        binding.cancelButton.setOnClickListener{
            Timber.d("CANCEL BUTTON PRESSED")
            Navigation.findNavController(binding.root).navigateUp()
        }

        viewModel.saveState.observe(this as LifecycleOwner, Observer { ss ->
            when(ss) {
                ShoeViewModel.SaveState.SAVE -> {
                    navigateToShoeList()
                    viewModel.onEventSaveComplete()
                }
            }
        })
        return binding.root
    }

    private fun navigateToShoeList() {
        findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
    }
}