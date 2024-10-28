package com.lection.lection_03

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class ListViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _items: MutableLiveData<MutableList<Int>> = savedStateHandle.getLiveData("items", mutableListOf(1, 2, 3, 4, 5))
    val items: LiveData<MutableList<Int>> get() = _items

    fun addItem() {
        val currentList = _items.value ?: mutableListOf()
        currentList.add(currentList.size + 1)
        _items.value = currentList
        savedStateHandle["items"] = currentList
    }

    fun setItems(items: List<Int>) {
        _items.value = items.toMutableList()
        savedStateHandle["items"] = items.toMutableList()
    }
}
