package com.example.inventory.data.moreroomexamples

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.sql.Date


/**
 * Base dao
 *
 * //https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1
 *
 * @param T
 * @constructor Create empty Base dao
 */
interface BaseDao<T> {
    /**
     * Insert an object in the database.
     *
     * @param obj the object to be inserted.
     */
    @Insert
    fun insert(obj: T)

    /**
     * Insert an array of objects in the database.
     *
     * @param obj the objects to be inserted.
     */
    @Insert
    fun insert(vararg obj: T)

    /**
     * Update an object from the database.
     *
     * @param obj the object to be updated
     */
    @Update
    fun update(obj: T)

    /**
     * Delete an object from the database
     *
     * @param obj the object to be deleted
     */
    @Delete
    fun delete(obj: T)
}

@Entity(tableName = "data")
data class Data(@PrimaryKey val id: String, val value: String)

@Dao
abstract class DataDao : BaseDao<Data> {

    /**
     * Get all data from the Data table.
     */
    @Query("SELECT * FROM Data")
    abstract fun getData(): List<Data>
}

@Entity
data class User(
    @PrimaryKey
    val id: String,
    val userName: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val dateOfBirth: Date,
    val registrationDate: Date
)


data class UserMinimal(
    val userId: String,
    val firstName: String,
    val lastName: String
)

/**
 * Data Access Object for the users table.
 */
@Dao
abstract class UserDao : BaseDao<User> {

    /**
     * Get a user by id.
     * @return the user from the table with a specific id.
     */
    @Query("SELECT * FROM USER WHERE id = :id")
    abstract fun getUserById(id: String): Flow<User>

    /**
     * Delete all users.
     */
    @Query("DELETE FROM USER")
    abstract fun deleteAllUsers()


    @Transaction
    open fun updateData(users: List<User>) {
        deleteAllUsers()
        insertAll(users)
    }

    @Insert
    abstract fun insertAll(users: List<User>)


    @Query("SELECT id, firstName, lastName FROM USER")
    abstract fun getUsersMinimal(): List<UserMinimal>

    @Query("SELECT * FROM USER")
    abstract fun getUsers(): List<User>

    @Query("SELECT * FROM Pets where owner = :userId")
    abstract fun getPetsForUser(userId: String): List<Pet>

}


@Entity(
    tableName = "pets",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("userId"),
        childColumns = arrayOf("owner")
    )]
)
data class Pet(
    @PrimaryKey val petId: String,
    val name: String,
    val owner: String
)


data class UserAndAllPets(
    val user: User,
    val pets: List<Pet> = ArrayList()
)

//class UserAndAllPets {
//    @Embedded
//    var user: User? = null
//    @Relation(parentColumn = "userId",
//    entityColumn = "owner")
//    var pets: List<Pet> = ArrayList()
//}