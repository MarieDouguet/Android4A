package com.esiea.android4a.presentation.main

sealed class CreateStatus
data class CreateSuccess(val email: String, val password: String): CreateStatus()
object CreateError : CreateStatus()