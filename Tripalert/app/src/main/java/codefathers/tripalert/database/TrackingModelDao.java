package codefathers.tripalert.database;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface TrackingModelDao {
    @Query("select * from TrackingModel")
    LiveData<List<TrackingModel>> getAllTrackings();

    @Query("select * from TrackingModel where isCreated = 0 ")
    LiveData<List<TrackingModel>> getFollowedTrackings();

    @Query("select * from TrackingModel where isCreated = 1")
    LiveData<List<TrackingModel>> getCreatedTracking();

    @Query("select * from TrackingModel where id = :id")
    TrackingModel getItemByID(int id);

    @Insert(onConflict = REPLACE)
    void addTracking(TrackingModel trackingModel);

    @Delete
    void deleteTracking(TrackingModel trackingModel);

}