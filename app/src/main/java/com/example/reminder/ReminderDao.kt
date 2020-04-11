package com.example.reminder

import androidx.room.*

//TO GET ACCESS TO THE DB WE USE A DAO
@Dao
interface ReminderDao {
    //ADD CRUD METHODS
    @Query("SELECT * FROM reminderTable")
    //By adding the suspend keyword to the method we have specified that this  method cannot be
    //called without using Coroutines.
    suspend fun getAllReminders(): List<Reminder>

    @Insert
    suspend fun insertReminder(reminder: Reminder)

    @Delete
    suspend fun deleteReminder(reminder: Reminder)

    @Update
    suspend fun updateReminder(reminder: Reminder)
}

//Room will take this interface and implement all the methods for us.