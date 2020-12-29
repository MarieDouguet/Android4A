package com.esiea.android4a.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.esiea.android4a.data.local.models.UserLocal

@Dao
interface DatabaseDao {
    @Query("SELECT * FROM USERS")
    fun getAll(): List<UserLocal>

    @Query("SELECT * FROM users WHERE users.email LIKE :email AND " + "users.password LIKE :password LIMIT 1")
    fun findByName(email: String, password: String): UserLocal?

    @Insert
    fun insert(user: UserLocal)

}