package com.github.akrucan.encepence.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class BoardWithTasks(
    @Embedded val board: Board,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val tasks: List<Task>
)
