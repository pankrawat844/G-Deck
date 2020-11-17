package com.app.g_deck.model
data class BookingID(
    val `data`: List<Data>
) {
    data class Data(
        val msg: Int,
        val success: Boolean
    )
}