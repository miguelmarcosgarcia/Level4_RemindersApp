package com.example.reminder

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

//We need to provide the class with the necessary annotation so that Room knows where
// and how to store the objects:

@Parcelize

//@Entity defines that this is an entity that needs to be stored in the database.
//tableName provides Room with a tableName we want to store this entity in.
// By default it will use the name of the object (Reminder).
@Entity(tableName = "reminderTable")
data class Reminder(

   @ColumnInfo(name = "reminder")
    var reminderText: String,
   //As you may know databases need a primary key. We want room to auto generate the id.
   @PrimaryKey(autoGenerate = true)

   //@ColumnInfo defines the aspects of the column.
   //We only give it a name. By default it would have used the name of the variable so
   // in this case we could’ve just used @ColumnInfo instead of
   // @ColumnInfo(name = “reminder”)
   @ColumnInfo(name = "id")
   var id:Long? = null //It’s marked as nullable (?) so that it’s optional in the constructor.

) :Parcelable

//Room creates an SQLite database using all objects annotated with @Entity.