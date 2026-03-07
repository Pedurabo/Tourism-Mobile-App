package com.example.tourism.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&\u00a8\u0006\u0012"}, d2 = {"Lcom/example/tourism/data/local/TourismDatabase;", "Landroidx/room/RoomDatabase;", "()V", "bookingDao", "Lcom/example/tourism/data/local/BookingDao;", "carDao", "Lcom/example/tourism/data/local/CarDao;", "flightDao", "Lcom/example/tourism/data/local/FlightDao;", "hotelDao", "Lcom/example/tourism/data/local/HotelDao;", "landmarkDao", "Lcom/example/tourism/data/local/LandmarkDao;", "notificationDao", "Lcom/example/tourism/data/local/NotificationDao;", "userDao", "Lcom/example/tourism/data/local/UserDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.example.tourism.data.model.Landmark.class, com.example.tourism.data.model.Booking.class, com.example.tourism.data.model.User.class, com.example.tourism.data.model.Notification.class, com.example.tourism.data.model.Hotel.class, com.example.tourism.data.model.Car.class, com.example.tourism.data.model.Flight.class}, version = 5, exportSchema = false)
@androidx.room.TypeConverters(value = {com.example.tourism.data.local.Converters.class})
public abstract class TourismDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.example.tourism.data.local.TourismDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.tourism.data.local.TourismDatabase.Companion Companion = null;
    
    public TourismDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.tourism.data.local.LandmarkDao landmarkDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.tourism.data.local.BookingDao bookingDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.tourism.data.local.UserDao userDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.tourism.data.local.NotificationDao notificationDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.tourism.data.local.HotelDao hotelDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.tourism.data.local.CarDao carDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.tourism.data.local.FlightDao flightDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/example/tourism/data/local/TourismDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/example/tourism/data/local/TourismDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.tourism.data.local.TourismDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}