package com.imhsp.podcastandroid.data

import androidx.room.*
import com.imhsp.podcastandroid.data.model.Favourite


@Dao
interface FavouriteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favourite: Favourite)

    @Query("SELECT * FROM favourites")
    fun getAllFavourites(): List<Favourite>

    @Delete
    suspend fun delete(favourite: Favourite)
}