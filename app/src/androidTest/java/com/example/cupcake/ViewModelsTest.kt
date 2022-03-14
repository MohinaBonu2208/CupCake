package com.example.cupcake

import junit.framework.TestCase.assertEquals
import org.junit.Test

class ViewModelsTest {
    @Test
    fun quantity_twelve_cupcakes() {
        val viewModel = OrderViewModel()
        viewModel.quantity.observeForever {}
        viewModel.setQuantity(12)
        assertEquals(12, viewModel.quantity.value)
    }

}