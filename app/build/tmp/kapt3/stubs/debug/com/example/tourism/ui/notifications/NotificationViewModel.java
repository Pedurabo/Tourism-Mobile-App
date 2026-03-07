package com.example.tourism.ui.notifications;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bJ\u000e\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0012R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0016"}, d2 = {"Lcom/example/tourism/ui/notifications/NotificationViewModel;", "Landroidx/lifecycle/ViewModel;", "notificationRepository", "Lcom/example/tourism/data/repository/NotificationRepository;", "(Lcom/example/tourism/data/repository/NotificationRepository;)V", "_notifications", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/example/tourism/data/model/Notification;", "notifications", "Lkotlinx/coroutines/flow/StateFlow;", "getNotifications", "()Lkotlinx/coroutines/flow/StateFlow;", "deleteNotification", "", "notification", "loadNotifications", "userId", "", "markAllAsRead", "markAsRead", "notificationId", "app_debug"})
public final class NotificationViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.tourism.data.repository.NotificationRepository notificationRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.tourism.data.model.Notification>> _notifications = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.tourism.data.model.Notification>> notifications = null;
    
    public NotificationViewModel(@org.jetbrains.annotations.NotNull()
    com.example.tourism.data.repository.NotificationRepository notificationRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.tourism.data.model.Notification>> getNotifications() {
        return null;
    }
    
    public final void loadNotifications(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
    }
    
    public final void markAsRead(@org.jetbrains.annotations.NotNull()
    java.lang.String notificationId) {
    }
    
    public final void markAllAsRead(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
    }
    
    public final void deleteNotification(@org.jetbrains.annotations.NotNull()
    com.example.tourism.data.model.Notification notification) {
    }
}