package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeListItemBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber


class ShoeListFragment : Fragment() {
    private val viewModel: ShoeViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListBinding
    private lateinit var linearLayout: LinearLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )

        viewModel.shoeList.observe(this as LifecycleOwner, {
            for (shoe in viewModel.shoeList.value!!) {
                Timber.d("SHOE LOOP: SHOE: ${shoe.getName()} SIZE: ${shoe.getCompany()}")
                val inBinding = ShoeListItemBinding.inflate(layoutInflater)
                inBinding.shoeData = shoe
                binding.shoeList.addView(inBinding.root)
            }
        })
        binding.fab.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_shoeListFragment_to_shoeDetailFragment)
        )
        return binding.root
    }
}