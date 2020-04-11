package com.example.reminder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

//@Database tells room that this class is a RoomDB and define the entities we wanna store in
//the DB (we only want to store Reminder entities)
@Database(entities = [Reminder::class], version = 1, exportSchema = false)
abstract class ReminderRoomDatabase : RoomDatabase() {

    //An abstract method for getting the implementation room makes sure that  the
    // reminderDao is added to the class.
    abstract fun reminderDao(): ReminderDao


    //Because we want the DB to be static we encapsulate the getDatabase function within
    // a companion object
    //If you need a function or a property to be tied to a class rather than to instances
    // of it (similar to @staticmethod in Python), you can declare it inside a companion object.
    companion object{

        //Note that in this case the constant DATABASE_NAME is also defined inside the companion
        // object rather than at the top level as we did before.
        private const val DATABASE_NAME = "REMINDER_DATABASE"

        @Volatile
        private var reminderRoomDatabaseInstance: ReminderRoomDatabase? = null

        fun getDatabase(context: Context): ReminderRoomDatabase? {
            if (reminderRoomDatabaseInstance == null) {
                synchronized(ReminderRoomDatabase::class.java) {
                    if (reminderRoomDatabaseInstance == null) {
                        reminderRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            ReminderRoomDatabase::class.java, DATABASE_NAME
                        )
                                /*
                                * By allowing the queries to be performed on the main thread we will
                                * face serious performance issues once the database queries increase
                                * in length.When a query is performed on the main thread then the
                                * user interface will stop working until the query is finished.
                                * In other words the screen will freeze for 3 seconds if the query
                                * takes 3 seconds. Enabling this was only meant for purposes of
                                * going through the basics of Room in the previous steps of this
                                * tutorial. Never allow this in a finished app.
                                */
                            //.allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return reminderRoomDatabaseInstance
        }

    }
}