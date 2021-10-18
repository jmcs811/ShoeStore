package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
        Timber.i("Adding Shoe method: $name $size")
        _shoeList.value?.add(Shoe(name, size, company, desc))
        for (shoe in _shoeList.value!!)
            Timber.i(shoe.getName())
        }

    fun onEventSave(name: String, size: Double, company: String, description: String) {
        Timber.i("Event SAVE")
//        var sizeDouble : Double = size
//        try {
//            sizeDouble = size.toDouble()
//        } catch (e: NumberFormatException) {
//            Timber.i("Invalid size entered")
//        }
        Timber.i("ONEVENTSAVE $size")
        Timber.i("Shoe: $name Description: $description sizeDouble: $size")
        addShoe(name, size, company, description)
        _saveState.value = SaveState.SAVE
    }
    fun onEventSaveComplete() {
        Timber.i("EVENT SAVE COMPLETE")
        _saveState.value = SaveState.NOPE
    }
}