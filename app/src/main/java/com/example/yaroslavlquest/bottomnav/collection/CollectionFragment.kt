package com.example.yaroslavlquest.bottomnav.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.yaroslavlquest.databinding.FragmentCollectionBinding

class CollectionFragment : Fragment() {
    private lateinit var binding: FragmentCollectionBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }
}