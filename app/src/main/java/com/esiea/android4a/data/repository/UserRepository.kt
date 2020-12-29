package com.esiea.android4a.data.repository

import com.esiea.android4a.data.local.DatabaseDao
import com.esiea.android4a.data.local.models.UserLocal
import com.esiea.android4a.data.local.models.toData
import com.esiea.android4a.data.local.models.toEntity
import com.esiea.android4a.domain.entity.User

class UserRepository(
    private val databaseDao: DatabaseDao
) {
    suspend fun createUser(user: User) {
        databaseDao.insert(user.toData())
    }

    fun getUser(email: String, password: String) : User? {
        val userLocal : UserLocal? = databaseDao.findByName(email, password)
        return userLocal?.toEntity()
    }

    fun isValidAccount(email: String, password: String): Boolean {

        val userAccount = databaseDao.findByName(email, password)
        return userAccount!!.password == password
    }

    fun insertUser(email: String, password: String) {
        val account = UserLocal(email, password)
        databaseDao.insert(account)
    }

    companion object {
        private var instance: UserRepository? = null

        fun getInstance(databasedao: DatabaseDao): UserRepository {
            if (instance == null) {
                instance = UserRepository(databasedao)
            }
            return instance!!
        }
    }
}