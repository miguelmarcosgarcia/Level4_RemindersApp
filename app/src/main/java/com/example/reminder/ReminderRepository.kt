package com.example.reminder

import android.content.Context

//The last step is to create a repository class which is responsible for using the DAO to make
// operations on the database. This prevents us from having to create and initialize the dao objects
// in the activity classes using the getDatabase method all the time. We just need to create a
// repository class now.

public class ReminderRepository(context: Context) {
    private var reminderDao: ReminderDao

    //The class constructor takes a Context object because we need this to access the database.
    // The reminderDao is constructed using the abstract method we added in the ReminderRoomDatabase
    // class.
    init {
        val reminderRoomDatabase =
            ReminderRoomDatabase.getDatabase(context)
        reminderDao = reminderRoomDatabase!!.reminderDao()
    }

    //The methods will use the reminderDao methods to make the actual operations. For example
    // insertReminder will insert a reminder in the database using the reminderDao insertReminder
    // method.
    suspend fun getAllReminders(): List<Reminder> {
        return reminderDao.getAllReminders()
    }

    suspend fun insertReminder(reminder:Reminder){
        reminderDao.insertReminder(reminder)
    }

    suspend fun deleteReminder(reminder: Reminder){
        reminderDao.deleteReminder(reminder)
    }

    suspend fun updateReminder(reminder: Reminder){
        reminderDao.updateReminder(reminder)
    }
}