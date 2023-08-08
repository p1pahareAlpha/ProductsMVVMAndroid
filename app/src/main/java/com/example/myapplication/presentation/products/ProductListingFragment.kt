package com.example.myapplication.presentation.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProductListingBinding
import com.example.myapplication.domain.products.model.Product
import com.example.myapplication.presentation.products.view.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class ProductListingFragment : Fragment() {

    private var _binding: FragmentProductListingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: ProductsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListingBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ProductsViewModel::class.java]
        val recyclerview = binding.recyclerview
        recyclerview.layoutManager = LinearLayoutManager(context)
        viewModel.callProductListingAPI()
        viewModel.productListLiveData.observe(this) {
           val productAdapter = ProductsAdapter(it?.products?: listOf())
          binding.textviewErrorMessage.visibility = GONE
            recyclerview.adapter = productAdapter
            // Applying OnClickListener to our Adapter
            productAdapter.setOnClickListener(
               object: ProductsAdapter.OnClickListener {
                override fun onClick(position: Int, product: Product) {
                   viewModel.callProductDetailsAPI(product.id)
                    findNavController().navigate(R.id.action_PL_to_PD)
                }
            })
        }
        viewModel.errorMessageLiveData.observe(this) {
            binding.textviewErrorMessage.text = it
            binding.textviewErrorMessage.visibility = VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}