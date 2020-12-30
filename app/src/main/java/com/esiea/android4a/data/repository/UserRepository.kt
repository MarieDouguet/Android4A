package com.esiea.android4a.data.repository

import com.esiea.android4a.data.local.DatabaseDao
import com.esiea.android4a.data.local.models.UserLocal
import com.esiea.android4a.data.local.models.toData
import com.esiea.android4a.data.local.models.toEntity
import com.esiea.android4a.domain.entity.User

class UserRepository(
    private val databaseDao: DatabaseDao
) {
    fun createUser(user: User) {
        databaseDao.insert(user.toData())
    }

    fun getUser(email: String, password: String): User? {
        val userLocal: UserLocal? = databaseDao.findByName(email, password)
        return userLocal?.toEntity()
    }

}