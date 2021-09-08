package com.example.fa21_group1_project1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

        /*
         * Following https://www.youtube.com/watch?v=lwAvI3WDXBY verbatim
         * Will adjust for our schema later
         */

        /*
         * replace firstName, lastName, and age with:
         * val username: String,
         * val password: String
         */

@Entity(tableName = "user_table")
data class User(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val firstName: String,
        val lastName: String,
        val age: Int
)