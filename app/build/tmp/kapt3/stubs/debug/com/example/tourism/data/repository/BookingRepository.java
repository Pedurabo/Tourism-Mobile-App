package com.example.tourism.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004JV\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\bH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00170\u0016J\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00170\u00162\u0006\u0010\u0007\u001a\u00020\bJ\u001e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u0010\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/example/tourism/data/repository/BookingRepository;", "", "bookingDao", "Lcom/example/tourism/data/local/BookingDao;", "(Lcom/example/tourism/data/local/BookingDao;)V", "createBooking", "Lcom/example/tourism/data/model/Booking;", "userId", "", "serviceId", "serviceType", "Lcom/example/tourism/data/model/ServiceType;", "travelDate", "Ljava/util/Date;", "numberOfPeople", "", "totalAmount", "", "returnDate", "specialRequirements", "(Ljava/lang/String;Ljava/lang/String;Lcom/example/tourism/data/model/ServiceType;Ljava/util/Date;IDLjava/util/Date;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllBookings", "Lkotlinx/coroutines/flow/Flow;", "", "getBookingsForUser", "updateBookingStatus", "", "bookingId", "status", "Lcom/example/tourism/data/model/BookingStatus;", "(Ljava/lang/String;Lcom/example/tourism/data/model/BookingStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class BookingRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.tourism.data.local.BookingDao bookingDao = null;
    
    public BookingRepository(@org.jetbrains.annotations.NotNull()
    com.example.tourism.data.local.BookingDao bookingDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.tourism.data.model.Booking>> getAllBookings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.tourism.data.model.Booking>> getBookingsForUser(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createBooking(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String serviceId, @org.jetbrains.annotations.NotNull()
    com.example.tourism.data.model.ServiceType serviceType, @org.jetbrains.annotations.NotNull()
    java.util.Date travelDate, int numberOfPeople, double totalAmount, @org.jetbrains.annotations.Nullable()
    java.util.Date returnDate, @org.jetbrains.annotations.Nullable()
    java.lang.String specialRequirements, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.tourism.data.model.Booking> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateBookingStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String bookingId, @org.jetbrains.annotations.NotNull()
    com.example.tourism.data.model.BookingStatus status, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}