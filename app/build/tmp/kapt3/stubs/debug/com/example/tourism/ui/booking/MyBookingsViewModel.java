package com.example.tourism.ui.booking;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\u000eR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u000f"}, d2 = {"Lcom/example/tourism/ui/booking/MyBookingsViewModel;", "Landroidx/lifecycle/ViewModel;", "bookingRepository", "Lcom/example/tourism/data/repository/BookingRepository;", "(Lcom/example/tourism/data/repository/BookingRepository;)V", "_bookings", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/example/tourism/data/model/Booking;", "bookings", "Lkotlinx/coroutines/flow/StateFlow;", "getBookings", "()Lkotlinx/coroutines/flow/StateFlow;", "loadUserBookings", "", "app_debug"})
public final class MyBookingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.tourism.data.repository.BookingRepository bookingRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.tourism.data.model.Booking>> _bookings = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.tourism.data.model.Booking>> bookings = null;
    
    public MyBookingsViewModel(@org.jetbrains.annotations.NotNull()
    com.example.tourism.data.repository.BookingRepository bookingRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.tourism.data.model.Booking>> getBookings() {
        return null;
    }
    
    public final void loadUserBookings() {
    }
}