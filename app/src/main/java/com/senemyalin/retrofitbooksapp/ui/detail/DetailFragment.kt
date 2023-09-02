package com.senemyalin.retrofitbooksapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.senemyalin.retrofitbooksapp.R
import com.senemyalin.retrofitbooksapp.common.loadImage
import com.senemyalin.retrofitbooksapp.common.viewBinding
import com.senemyalin.retrofitbooksapp.data.model.GetBookDetailResponse
import com.senemyalin.retrofitbooksapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)

    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel by viewModels<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBookDetail(args.bookId)
        observeData()
    }

    private fun observeData() {
        with(binding){

            viewModel.bookDetailLiveData.observe(viewLifecycleOwner) { book ->
                if (book != null) {
                    textViewTitle.text = book.name
                    textViewPublisher.text = "Publisher: ${book.publisher}"
                    textViewPrice.text = "Price: ${book.price} ₺"
                    imageView.loadImage(book.image_url)

                    if(book.is_best_seller == true){
                        textViewBestSeller.visibility = View.VISIBLE
                    }else{
                        textViewBestSeller.visibility = View.INVISIBLE
                    }
                } else {
                    Log.e("GetBookDetails", "Book details are null")
                }

            }

            viewModel.errorMessageLiveData.observe(viewLifecycleOwner){
                Log.e("GetBookDetails", it.orEmpty())
            }

        }
    }
}