package com.anyemi.omrooms.db;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface BookingDao {

    @Query("SELECT * FROM RoomBooking")
    DataSource.Factory<Integer, RoomBooking> allRoomBookingDetails();

    @Query("Update RoomBooking SET no_of_room_booked = :count WHERE hotel_id = :pId")
    void updateRoomCount(int count,int pId);

    @Insert
    void insert(RoomBooking roomBooking);

    @Delete
    void delete(RoomBooking roomBooking);

}
