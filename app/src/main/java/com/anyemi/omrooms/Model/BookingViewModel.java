package com.anyemi.omrooms.Model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.anyemi.omrooms.db.RoomBooking;
import com.anyemi.omrooms.db.RoomDb;

public class BookingViewModel extends AndroidViewModel {

    private LiveData<PagedList<RoomBooking>> roomsListLiveData;
//    private LiveData<PagedList<News>> newsSearchListLiveData;
    private Application application;
    public BookingViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    //LiveData
    //bookmark is used to retrieve categories news from room

    public LiveData<PagedList<RoomBooking>> getRoomListLiveData() {

        roomsListLiveData = null;
        DataSource.Factory<Integer,RoomBooking> factory = RoomDb.getsInstance(application).bookingDao().allRoomBookingDetails();
        //config the pagedList
        //setPageSize(5) retrieves 5 sets of news object in single instance
        PagedList.Config pagConfig = new PagedList.Config.Builder().setPageSize(5).setEnablePlaceholders(false).build();
        LivePagedListBuilder<Integer, RoomBooking> pagedListBuilder = new LivePagedListBuilder(factory,pagConfig);
        roomsListLiveData = pagedListBuilder.build();
        return roomsListLiveData;
    }


}

