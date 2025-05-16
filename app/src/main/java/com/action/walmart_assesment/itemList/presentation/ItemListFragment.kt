package com.action.walmart_assesment.itemList.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.action.walmart_assesment.databinding.FragmentItemListBinding
import com.action.walmart_assesment.itemList.data.repositoryImpl.CountryRepositoryImpl
import com.action.walmart_assesment.itemList.presentation.recyclerview.ItemListAdapter
import kotlinx.coroutines.launch

class ItemListFragment: Fragment() {
    private lateinit var _binding: FragmentItemListBinding
    private val itemListViewModel: ItemListViewModel by viewModels {
        object: ViewModelProvider.Factory {
            override fun <T: ViewModel> create(modelClass: Class<T>): T {
                if(modelClass.isAssignableFrom(ItemListViewModel::class.java)) {
                    val countryRepository = CountryRepositoryImpl()
                    return ItemListViewModel(countryRepository = countryRepository) as T
                }
                throw IllegalStateException("Unknown ViewModel class")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = _binding.recyclerView
        val adapter = ItemListAdapter(data = emptyList())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        lifecycleScope.launch {
            itemListViewModel.countryState.collect {
                when(it) {
                    is UiState.ERROR -> {
                        _binding.progressBar.visibility = View.INVISIBLE
                        _binding.errorMessage.visibility = View.VISIBLE
                    }
                    UiState.LOADING -> {
                        _binding.progressBar.visibility = View.VISIBLE
                    }
                    is UiState.SUCCESS -> {
                        _binding.progressBar.visibility = View.INVISIBLE
                        val (data) = it
                        adapter.updateData(data)
                    }
                }
            }
        }
    }

}