package com.example.myshophanivia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myshophanivia.databinding.FragmentCheckoutBinding

class CheckoutFragment : Fragment() {
    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            // 1. Ambil data nama produk dari HomeFragment
            val args: CheckoutFragmentArgs by navArgs()
            txtProductName.text = args.productName

            // 2. Navigasi ke AddressFragment saat input alamat diklik
            edtAddress.setOnClickListener {
                val action = CheckoutFragmentDirections.actionCheckoutFragmentToAddressFragment()
                findNavController().navigate(action)
            }

            // 3. Menerima data balikan alamat dari AddressFragment
            findNavController().currentBackStackEntry
                ?.savedStateHandle?.let { handle ->
                    handle.getLiveData<String>("address")
                        .observe(viewLifecycleOwner) { res ->
                            edtAddress.setText(res)
                        }
                }

            // 4. Kembali ke HomeFragment saat tombol selesai diklik
            btnDone.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}