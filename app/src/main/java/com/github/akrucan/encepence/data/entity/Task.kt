package com.github.akrucan.encepence.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity(
    tableName = "tasks",
    foreignKeys = [ForeignKey(
        entity = Board::class,
        parentColumns = ["id"],
        childColumns = ["boardId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("boardId")
    ]
)
data class Task(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val boardId: Long = 0L,
    val name: String,
    val description: String? = null,
    val dueDate: ZonedDateTime? = null,
    val priority: Priority? = Priority.OPTIONAL
)

enum class Priority(val string: String){
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low"),
    OPTIONAL("Optional")
}
