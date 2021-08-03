package com.example.xcriticaltestapp.dataBase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projectDb")
data class ProjectEntity(
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "scenario") val scenario: String?,
    @ColumnInfo(name = "date") val date: String?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var projectId: Long = 0
}