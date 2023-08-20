package com.senemyalin.retrofitbooksapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.senemyalin.retrofitbooksapp.common.loadImage
import com.senemyalin.retrofitbooksapp.data.model.Book
import com.senemyalin.retrofitbooksapp.databinding.ItemBookBinding

class BooksAdapter(
    private val onBookClick: (Int) -> Unit
) : ListAdapter<Book, BooksAdapter.BookViewHolder>(BookDiffCallBack()) {

    private val bookList = mutableListOf<Book>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
        BookViewHolder(
            ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) =
        holder.bind(bookList[position])

    inner class BookViewHolder(
        private val binding: ItemBookBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) =
            with(binding) {
                textViewName.text = book.name
                textViewPrice.text = "${book.price} ₺"

                imageView.loadImage(book.image_url)

                if(book.is_best_seller == true){
                    textViewBestSeller.visibility = View.VISIBLE
                }else{
                    textViewBestSeller.visibility = View.INVISIBLE
                }

                root.setOnClickListener {
                    book.id?.let(onBookClick)
                }
            }
    }


    fun updateList(list: List<Book>) {
        bookList.clear()
        bookList.addAll(list)
        notifyItemRangeChanged(0, list.size)
    }

    override fun getItemCount() = bookList.size

    class BookDiffCallBack : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }

}