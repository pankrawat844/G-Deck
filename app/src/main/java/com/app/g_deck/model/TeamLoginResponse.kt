package com.app.g_deck.model

data class TeamLoginResponse(
    val message: String?,
    val response: Response?,
    val status: Boolean?
) {
    data class Response(
        val company: String?,
        val email: String?,
        val id: String?,
        val name: String?,
        val password: String?,
        val phone: String?,
        val userid: String?
    )
}