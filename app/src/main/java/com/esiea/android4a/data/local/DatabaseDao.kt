package com.esiea.android4a.data.local

import androidx.room.*
import com.esiea.android4a.data.local.models.UserLocal

@Dao
interface DatabaseDao {
    @Query("SELECT * FROM userlocal")
    fun getAll(): List<UserLocal>

    @Query("SELECT * FROM userlocal WHERE email LIKE :email AND password LIKE :password LIMIT 1")
    fun findByName(email: String, password: String): UserLocal?

    @Insert
    fun insert(user: UserLocal)

    @Delete
    fun delete(user: UserLocal)
}