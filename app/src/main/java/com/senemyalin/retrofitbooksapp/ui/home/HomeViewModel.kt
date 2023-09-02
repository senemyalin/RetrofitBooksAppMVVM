package com.senemyalin.retrofitbooksapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.senemyalin.retrofitbooksapp.data.model.Book
import com.senemyalin.retrofitbooksapp.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: BookRepository
): ViewModel(){

    private var _allBooksLiveData = MutableLiveData<List<Book>?>()
    val allBooksLiveData: LiveData<List<Book>?>
        get() = _allBooksLiveData

    private var _errorMessageLiveData = MutableLiveData<String>()
    val errorMessageLiveData: LiveData<String>
        get() = _errorMessageLiveData

    private var _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    init {
        _allBooksLiveData = repository.allBooksLiveData
        _errorMessageLiveData = repository.errorMessageLiveData
        _loadingLiveData = repository.loadingLiveData
    }
    fun getBooks(){
        repository.getBooks()
    }

}