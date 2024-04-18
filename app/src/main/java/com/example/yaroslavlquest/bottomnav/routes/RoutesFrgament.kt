package com.example.yaroslavlquest.bottomnav.routes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.yaroslavlquest.databinding.FrgamentRoutesBinding

class RoutesFragment : Fragment() {
    private lateinit var binding: FrgamentRoutesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FrgamentRoutesBinding.inflate(inflater, container, false)
        return binding.root
    }
}