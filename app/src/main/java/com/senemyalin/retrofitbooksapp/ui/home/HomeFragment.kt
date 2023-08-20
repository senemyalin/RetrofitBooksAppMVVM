package com.senemyalin.retrofitbooksapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.senemyalin.retrofitbooksapp.R
import com.senemyalin.retrofitbooksapp.common.viewBinding
import com.senemyalin.retrofitbooksapp.data.RetrofitProvider
import com.senemyalin.retrofitbooksapp.data.model.GetAllBooksResponse
import com.senemyalin.retrofitbooksapp.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val booksAdapter = BooksAdapter(::onBookClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewBooks.adapter = booksAdapter
        getBooks()
    }

    private fun getBooks() {
        RetrofitProvider.bookService?.getAllBooks()
            ?.enqueue(object : Callback<GetAllBooksResponse> {
                override fun onResponse(
                    call: Call<GetAllBooksResponse>,
                    response: Response<GetAllBooksResponse>
                ) {
                    val result = response.body()?.books

                    if (result.isNullOrEmpty().not()) {
                        booksAdapter.updateList(result!!)
                    }
                }

                override fun onFailure(call: Call<GetAllBooksResponse>, t: Throwable) {
                    Log.e("GetAllBooks", t.message.orEmpty())
                }
            })
    }

    private fun onBookClick(bookId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(bookId)
        findNavController().navigate(action)
    }
}

