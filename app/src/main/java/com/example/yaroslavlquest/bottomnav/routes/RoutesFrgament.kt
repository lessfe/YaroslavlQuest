package com.example.yaroslavlquest.bottomnav.routes

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.yaroslavlquest.RegistetActivity
import com.example.yaroslavlquest.bottomnav.profile.ProfileFragment
import com.example.yaroslavlquest.databinding.FragmentProfileBinding
import com.example.yaroslavlquest.databinding.FrgamentRoutesBinding


class RoutesFragment : Fragment() {
    private lateinit var linLayout: LinearLayout
    private lateinit var binding: FrgamentRoutesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FrgamentRoutesBinding.inflate(inflater, container, false)
        val fragmentMap: MutableMap<Int, Fragment> = HashMap()

        val view = inflater.inflate(com.example.yaroslavlquest.R.layout.frgament_routes, container, false)

        // Находим LinearLayout и устанавливаем OnClickListener
        val linLayout = view.findViewById<LinearLayout>(com.example.yaroslavlquest.R.id.lin_1)
        linLayout.setOnClickListener {
            // Переход к другому фрагменту
            navigateToAnotherFragment()
        }

        return view
    }
    private fun navigateToAnotherFragment() {

        // Создаем новый экземпляр фрагмента, который хотим открыть
        val anotherFragment = RouteCurrent()

        // Начинаем транзакцию
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        // Заменяем текущий фрагмент на новый
        transaction.replace(com.example.yaroslavlquest.R.id.fragment_container, anotherFragment)
        // Применяем транзакцию
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
    }
    private fun handleLinLayoutClick() {

    }
}