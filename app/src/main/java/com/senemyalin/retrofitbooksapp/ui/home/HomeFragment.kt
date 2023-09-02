package com.senemyalin.retrofitbooksapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.senemyalin.retrofitbooksapp.R
import com.senemyalin.retrofitbooksapp.common.loadImage
import com.senemyalin.retrofitbooksapp.common.viewBinding
import com.senemyalin.retrofitbooksapp.data.model.GetAllBooksResponse
import com.senemyalin.retrofitbooksapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val booksAdapter = BooksAdapter(::onBookClick)
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewBooks.adapter = booksAdapter
        viewModel.getBooks()
        observeData()
    }

    private fun observeData() {
        with(binding) {

            viewModel.allBooksLiveData.observe(viewLifecycleOwner) { books ->
                if (books != null) {
                    booksAdapter.updateList(books)
                } else {
                    Log.e("GetBooks", "Books are null")
                }

            }

            viewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
                Log.e("GetBooks", it.orEmpty())
            }

        }
    }

    private fun onBookClick(bookId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(bookId)
        findNavController().navigate(action)
    }
}

