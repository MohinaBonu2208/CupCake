package com.example.cupcake

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_CAKE = 2.00
private const val EXTRA_PRICE_DATE = 3.00

class OrderViewModel : ViewModel() {

    val dateOptions = getPickupOptions()

    private var _quantity = MutableLiveData<Int>(0)
    val quantity: LiveData<Int> = _quantity

    private var _flavour = MutableLiveData<String>("")
    val flavour: LiveData<String> = _flavour

    private var _date = MutableLiveData<String>(dateOptions[0])
    val date: LiveData<String> = _date

    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double> = _price


    fun setQuantity(numberCakes: Int) {
        _quantity.value = numberCakes
        updatePrice()
    }

    fun setFlavour(desiredflavour: String) {
        _flavour.value = desiredflavour
    }

    fun setDate(pickupDate: String) {
        _date.value = pickupDate
        updatePrice(true)
    }

    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    private fun updatePrice(inDatePage: Boolean = false) {
        var initialPrice = (quantity.value ?: 0) * PRICE_PER_CAKE
        if (inDatePage) {
            if (dateOptions[0] == _date.value) {
                initialPrice += EXTRA_PRICE_DATE
            }
        }
        _price.value = initialPrice
    }

    fun resetOrder() {
        _price.value = 0.0
        _date.value = ""
        _flavour.value = ""
        _quantity.value = 0


    }
}