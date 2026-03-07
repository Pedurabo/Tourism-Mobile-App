package com.example.tourism.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006J\u0018\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\fJ\u000e\u0010\r\u001a\u00020\u000eH\u0082@\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/example/tourism/data/repository/HotelRepository;", "", "hotelDao", "Lcom/example/tourism/data/local/HotelDao;", "(Lcom/example/tourism/data/local/HotelDao;)V", "getAllHotels", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/tourism/data/model/Hotel;", "getHotelById", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "seedHotels", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class HotelRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.tourism.data.local.HotelDao hotelDao = null;
    
    public HotelRepository(@org.jetbrains.annotations.NotNull()
    com.example.tourism.data.local.HotelDao hotelDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.tourism.data.model.Hotel>> getAllHotels() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getHotelById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.tourism.data.model.Hotel> $completion) {
        return null;
    }
    
    private final java.lang.Object seedHotels(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}