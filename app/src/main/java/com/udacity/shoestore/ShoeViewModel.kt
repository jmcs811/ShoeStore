package com.udacity.shoestore

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel : ViewModel() {

    enum class SaveState {
        SAVE,
        NOPE
    }

    private var _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private var _saveState = MutableLiveData<SaveState>()
    val saveState: LiveData<SaveState>
        get() = _saveState

    init {
        Timber.i("ShoeViewModel init")
        _shoeList.value = mutableListOf()
        addShoe("Slip-ons", 11.0, "Vans", "Vans Classic Slip-ons")
        _saveState.value = SaveState.NOPE
    }

    fun addShoe(name: String, size: Double, company: String, desc: String) {
        Timber.i("Adding Shoe")
        _shoeList.value?.add(Shoe(name, size, company, desc))
        Timber.i(_shoeList.value?.joinToString())
    }

    fun onEventSave(name: String, size: String, company: String, description: String) {
        var sizeDouble : Double = 0.0
        try {
            sizeDouble = size.toDouble()
        } catch (e: NumberFormatException) {
            Timber.i("Invalid size entered")
        }
        addShoe(name, sizeDouble, company, description)
        _saveState.value = SaveState.SAVE
    }
    fun onEventSaveComplete() {
        _saveState.value = SaveState.NOPE
    }
}