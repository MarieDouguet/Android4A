package com.esiea.android4a.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esiea.android4a.domain.entity.User
import com.esiea.android4a.domain.usecase.CreateUserUseCase
import com.esiea.android4a.domain.usecase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    val loginLiveData: MutableLiveData<LoginStatus> = MutableLiveData()

    fun onClickedLogin(emailUser: String, passwordUser: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user: User? = getUserUseCase.invoke(emailUser, passwordUser)
            val loginStatus = if (user != null) {
                LoginSuccess(user.email, user.password)
            } else {
                LoginError
            }

            withContext(Dispatchers.Main){
                loginLiveData.value = loginStatus
            }
        }
    }
}