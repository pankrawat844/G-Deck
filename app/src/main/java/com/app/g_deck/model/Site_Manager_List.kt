package com.app.g_deck.model

import java.io.Serializable

data class Site_Manager_List(
    val `data`: List<Data>?
) {
    data class Data(
        val booked_by: String?,
        val booking_id: String?,
        val date: String?,
        val dismantle: String?,
        val erect: String?,
        val ff: String?,
        val gf: String?,
        val id: String?,
        val manager_id: String?,
        val plot: String?,
        val props: String?,
        val completed: String?,
        val sf: String?,
        val site: String?,
        val special_instruction: String?,
        val status: String?
    ):Serializable
}