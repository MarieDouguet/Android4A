package com.esiea.android4a.presentation.createAccount

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esiea.android4a.domain.entity.User
import com.esiea.android4a.domain.usecase.CreateUserUseCase
import com.esiea.android4a.presentation.main.CreateError
import com.esiea.android4a.presentation.main.CreateStatus
import com.esiea.android4a.presentation.main.CreateSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CreateAccountViewModel(
    private val createUserUseCase: CreateUserUseCase,
) : ViewModel(){
    val createLiveData : MutableLiveData<CreateStatus> = MutableLiveData()


    fun onClickedCreate(emailNewUser: String, passwordNewUser: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newUser = createUserUseCase.invoke(User(emailNewUser, passwordNewUser))
            val createStatus = if(newUser != null){
                CreateSuccess(emailNewUser, passwordNewUser)
            }else{
                CreateError
            }
            withContext(Dispatchers.Main){
                createLiveData.value = createStatus
            }
        }
    }
}