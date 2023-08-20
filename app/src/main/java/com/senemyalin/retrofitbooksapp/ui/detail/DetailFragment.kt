package com.senemyalin.retrofitbooksapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.senemyalin.retrofitbooksapp.R
import com.senemyalin.retrofitbooksapp.common.loadImage
import com.senemyalin.retrofitbooksapp.common.viewBinding
import com.senemyalin.retrofitbooksapp.data.RetrofitProvider
import com.senemyalin.retrofitbooksapp.data.model.GetBookDetailResponse
import com.senemyalin.retrofitbooksapp.databinding.FragmentDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)

    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBookDetail(args.bookId)
    }

    private fun getBookDetail(id: Int) {
        RetrofitProvider.bookService?.getBookDetail(id)
            ?.enqueue(object : Callback<GetBookDetailResponse> {
                override fun onResponse(
                    call: Call<GetBookDetailResponse>,
                    response: Response<GetBookDetailResponse>
                ) {
                    val result = response.body()?.book

                    if (result != null) {
                        with(binding) {
                            textViewTitle.text = result.name
                            textViewPublisher.text = "Publisher: ${result.publisher}"
                            textViewPrice.text = "Price: ${result.price} ₺"
                            imageView.loadImage(result.image_url)

                            if(result.is_best_seller == true){
                                textViewBestSeller.visibility = View.VISIBLE
                            }else{
                                textViewBestSeller.visibility = View.INVISIBLE
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<GetBookDetailResponse>, t: Throwable) {
                    Log.e("GetProducts", t.message.orEmpty())
                }
            })
    }
}