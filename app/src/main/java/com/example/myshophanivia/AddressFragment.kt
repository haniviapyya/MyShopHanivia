package com.example.myshophanivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myshophanivia.R // <--- Pastikan R milik package project kamu yang di-import
import com.example.myshophanivia.databinding.FragmentAddressBinding

class AddressFragment : Fragment() {
    private var _binding: FragmentAddressBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            // Mengambil array provinsi dari R.array.provinces
            val provinces = resources.getStringArray(R.array.provinces)
            val adapterProvinces = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                provinces
            )
            adapterProvinces.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // Akses ViewBinding spinner_provinces (diakses menjadi spinnerProvinces)
            spinnerProvinces.adapter = adapterProvinces

            // Akses ViewBinding btn_done (diakses menjadi btnDone)
            btnDone.setOnClickListener {
                findNavController().apply {
                    previousBackStackEntry
                        ?.savedStateHandle?.set(
                            "address",
                            spinnerProvinces.selectedItem.toString()
                        )
                }.navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}