package com.example.myapplication.presentation.products

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.common.AppConstants
import com.example.myapplication.databinding.FragmentProductDetailBinding
import com.example.myapplication.presentation.products.view.ImageSlideAdapter
import dagger.hilt.android.AndroidEntryPoint
import me.relex.circleindicator.CircleIndicator

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProductsViewModel
    lateinit var viewPagerAdapter: ImageSlideAdapter
     lateinit var indicator: CircleIndicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ProductsViewModel::class.java]
        viewModel.selectedProductLiveData.observe(this) {

                viewPagerAdapter = ImageSlideAdapter(requireContext(), arrayListOf<String>(*it.images.split("|").toTypedArray()))
               binding.viewpager.adapter = viewPagerAdapter
                indicator = binding.indicator
                indicator.setViewPager(binding.viewpager)
            binding.textviewTitle.text = it.title
            var price : StringBuilder = java.lang.StringBuilder(AppConstants.RUPEE_SIGN)
                .append(it.price)
            binding.textviewPrice.text = price.toString()
            binding.textviewDescription.text = it.description
        }

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_PD_to_PL)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}