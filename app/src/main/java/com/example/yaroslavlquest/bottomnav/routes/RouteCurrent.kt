package com.example.yaroslavlquest.bottomnav.routes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.example.yaroslavlquest.R
import com.example.yaroslavlquest.bottomnav.map.MapFragment
import com.example.yaroslavlquest.databinding.FragmentRouteCurrentBinding
import androidx.fragment.app.FragmentActivity
import com.example.yaroslavlquest.databinding.ActivityMainBinding


class RouteCurrent : Fragment() {

    private lateinit var btn: Button
    private lateinit var binding: ActivityMainBinding

    companion object {
        fun newInstance() = RouteCurrent()
    }

    private lateinit var viewModel: RouteCurrentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = inflater.inflate(R.layout.fragment_route_current, container, false)
        val btn = view.findViewById<Button>(com.example.yaroslavlquest.R.id.btn_1)
        btn.setOnClickListener {
            navigateToAnotherFragment()

        }

        return view
    }
    private fun navigateToAnotherFragment() {
        val fragmentMap: MutableMap<Int, Fragment> = HashMap()
        fragmentMap[R.id.map] = MapFragment()
        // Создаем новый экземпляр фрагмента, который хотим открыть
        val anotherFragment = MapFragment()

        // Начинаем транзакцию
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        // Заменяем текущий фрагмент на новый
        transaction.replace(com.example.yaroslavlquest.R.id.fragment_container, anotherFragment)
        // Применяем транзакцию
        transaction.commit()

        val fragment = fragmentMap[R.id.map]
        parentFragmentManager.beginTransaction().replace(binding.fragmentContainer.id, fragment!!).commit()


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RouteCurrentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}