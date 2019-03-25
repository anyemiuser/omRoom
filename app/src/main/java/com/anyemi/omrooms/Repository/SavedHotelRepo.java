package com.anyemi.omrooms.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.os.AsyncTask;

import com.anyemi.omrooms.db.BookingDao;
import com.anyemi.omrooms.db.RoomBooking;
import com.anyemi.omrooms.db.RoomDb;

import java.util.List;

public class SavedHotelRepo {

    private BookingDao bookingDao;
    private DataSource.Factory<Integer, RoomBooking> savedHotel;

    public SavedHotelRepo(Application application) {
        RoomDb db = RoomDb.getsInstance(application);
        bookingDao = db.bookingDao();
        savedHotel = bookingDao.allSavedHotelDetails();
    }

    public DataSource.Factory<Integer, RoomBooking> getSavedHotel() {
        return savedHotel;
    }


    public void insert(RoomBooking hotel) {
        new insertAsyncTask(bookingDao).execute(hotel);
    }

    private static class insertAsyncTask extends AsyncTask<RoomBooking, Void, Void> {

        private BookingDao mAsyncTaskDao;

        insertAsyncTask(BookingDao bookingDao) {
            mAsyncTaskDao = bookingDao;
        }

        @Override
        protected Void doInBackground(RoomBooking... roomBookings) {
            mAsyncTaskDao.insert(roomBookings[0]);
            return null;
        }

    }
}
