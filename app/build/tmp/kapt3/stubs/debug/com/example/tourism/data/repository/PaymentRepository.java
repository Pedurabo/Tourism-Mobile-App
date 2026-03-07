package com.example.tourism.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\b\u001a\u00020\tJ&\u0010\n\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/example/tourism/data/repository/PaymentRepository;", "", "()V", "payments", "", "Lcom/example/tourism/data/model/Payment;", "getPaymentsByBooking", "", "bookingId", "", "processPayment", "amount", "", "method", "Lcom/example/tourism/data/model/PaymentMethod;", "(Ljava/lang/String;DLcom/example/tourism/data/model/PaymentMethod;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class PaymentRepository {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.tourism.data.model.Payment> payments = null;
    
    public PaymentRepository() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object processPayment(@org.jetbrains.annotations.NotNull()
    java.lang.String bookingId, double amount, @org.jetbrains.annotations.NotNull()
    com.example.tourism.data.model.PaymentMethod method, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.tourism.data.model.Payment> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.tourism.data.model.Payment> getPaymentsByBooking(@org.jetbrains.annotations.NotNull()
    java.lang.String bookingId) {
        return null;
    }
}