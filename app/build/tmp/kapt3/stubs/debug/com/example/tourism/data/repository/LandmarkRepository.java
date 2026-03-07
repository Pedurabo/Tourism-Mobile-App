package com.example.tourism.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\r0\fJ\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\rH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\u00020\u0006H\u0082@\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/example/tourism/data/repository/LandmarkRepository;", "", "landmarkDao", "Lcom/example/tourism/data/local/LandmarkDao;", "(Lcom/example/tourism/data/local/LandmarkDao;)V", "addLandmark", "", "landmark", "Lcom/example/tourism/data/model/Landmark;", "(Lcom/example/tourism/data/model/Landmark;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteLandmark", "getAllLandmarks", "Lkotlinx/coroutines/flow/Flow;", "", "getMajorLandmarks", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "seedDatabase", "app_debug"})
public final class LandmarkRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.tourism.data.local.LandmarkDao landmarkDao = null;
    
    public LandmarkRepository(@org.jetbrains.annotations.NotNull()
    com.example.tourism.data.local.LandmarkDao landmarkDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.tourism.data.model.Landmark>> getAllLandmarks() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getMajorLandmarks(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.tourism.data.model.Landmark>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addLandmark(@org.jetbrains.annotations.NotNull()
    com.example.tourism.data.model.Landmark landmark, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteLandmark(@org.jetbrains.annotations.NotNull()
    com.example.tourism.data.model.Landmark landmark, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object seedDatabase(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}