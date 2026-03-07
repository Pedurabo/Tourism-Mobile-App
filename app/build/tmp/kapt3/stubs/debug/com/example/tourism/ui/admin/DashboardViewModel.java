package com.example.tourism.ui.admin;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJJ\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\'2\b\b\u0002\u0010(\u001a\u00020\'2\b\b\u0002\u0010)\u001a\u00020\'J\u000e\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020,J\u0006\u0010-\u001a\u00020 J\u0006\u0010.\u001a\u00020 J\u0010\u0010/\u001a\u00020 2\b\u00100\u001a\u0004\u0018\u00010\u000bJ\u000e\u00101\u001a\u00020 2\u0006\u00102\u001a\u00020\u000fJ\u0016\u00103\u001a\u00020 2\u0006\u00104\u001a\u00020\u000f2\u0006\u00105\u001a\u00020\u000bJ\u000e\u00106\u001a\u00020 2\u0006\u0010+\u001a\u00020,J\u0016\u00107\u001a\u00020 2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00120\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006<"}, d2 = {"Lcom/example/tourism/ui/admin/DashboardViewModel;", "Landroidx/lifecycle/ViewModel;", "bookingRepository", "Lcom/example/tourism/data/repository/BookingRepository;", "landmarkRepository", "Lcom/example/tourism/data/repository/LandmarkRepository;", "userDao", "Lcom/example/tourism/data/local/UserDao;", "(Lcom/example/tourism/data/repository/BookingRepository;Lcom/example/tourism/data/repository/LandmarkRepository;Lcom/example/tourism/data/local/UserDao;)V", "_bookingStatusFilter", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/tourism/data/model/BookingStatus;", "_dashboardState", "Lcom/example/tourism/ui/admin/DashboardUiState;", "_searchQuery", "", "_uiEvent", "Lkotlinx/coroutines/channels/Channel;", "Lcom/example/tourism/ui/admin/DashboardUiEvent;", "bookingStatusFilter", "Lkotlinx/coroutines/flow/StateFlow;", "getBookingStatusFilter", "()Lkotlinx/coroutines/flow/StateFlow;", "dashboardState", "getDashboardState", "searchQuery", "getSearchQuery", "uiEvent", "Lkotlinx/coroutines/flow/Flow;", "getUiEvent", "()Lkotlinx/coroutines/flow/Flow;", "addTourismSite", "", "name", "location", "description", "category", "imageUrl", "price", "", "lat", "long", "deleteLandmark", "landmark", "Lcom/example/tourism/data/model/Landmark;", "exportReport", "loadDashboardData", "setBookingStatusFilter", "status", "setSearchQuery", "query", "updateBookingStatus", "bookingId", "newStatus", "updateLandmark", "updateUserRole", "user", "Lcom/example/tourism/data/model/User;", "newRole", "Lcom/example/tourism/data/model/UserType;", "app_debug"})
public final class DashboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.tourism.data.repository.BookingRepository bookingRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.tourism.data.repository.LandmarkRepository landmarkRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.tourism.data.local.UserDao userDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.tourism.ui.admin.DashboardUiState> _dashboardState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.tourism.ui.admin.DashboardUiState> dashboardState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.tourism.data.model.BookingStatus> _bookingStatusFilter = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.tourism.data.model.BookingStatus> bookingStatusFilter = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.channels.Channel<com.example.tourism.ui.admin.DashboardUiEvent> _uiEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.example.tourism.ui.admin.DashboardUiEvent> uiEvent = null;
    
    public DashboardViewModel(@org.jetbrains.annotations.NotNull()
    com.example.tourism.data.repository.BookingRepository bookingRepository, @org.jetbrains.annotations.NotNull()
    com.example.tourism.data.repository.LandmarkRepository landmarkRepository, @org.jetbrains.annotations.NotNull()
    com.example.tourism.data.local.UserDao userDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.tourism.ui.admin.DashboardUiState> getDashboardState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.tourism.data.model.BookingStatus> getBookingStatusFilter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.example.tourism.ui.admin.DashboardUiEvent> getUiEvent() {
        return null;
    }
    
    public final void loadDashboardData() {
    }
    
    public final void setSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void setBookingStatusFilter(@org.jetbrains.annotations.Nullable()
    com.example.tourism.data.model.BookingStatus status) {
    }
    
    public final void updateBookingStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String bookingId, @org.jetbrains.annotations.NotNull()
    com.example.tourism.data.model.BookingStatus newStatus) {
    }
    
    public final void deleteLandmark(@org.jetbrains.annotations.NotNull()
    com.example.tourism.data.model.Landmark landmark) {
    }
    
    public final void addTourismSite(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String location, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl, double price, double lat, double p7_1663806) {
    }
    
    public final void updateLandmark(@org.jetbrains.annotations.NotNull()
    com.example.tourism.data.model.Landmark landmark) {
    }
    
    public final void updateUserRole(@org.jetbrains.annotations.NotNull()
    com.example.tourism.data.model.User user, @org.jetbrains.annotations.NotNull()
    com.example.tourism.data.model.UserType newRole) {
    }
    
    public final void exportReport() {
    }
}