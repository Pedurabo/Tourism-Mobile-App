package com.example.tourism.ui.booking;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0012"}, d2 = {"Lcom/example/tourism/ui/booking/PaymentViewModel;", "Landroidx/lifecycle/ViewModel;", "paymentRepository", "Lcom/example/tourism/data/repository/PaymentRepository;", "(Lcom/example/tourism/data/repository/PaymentRepository;)V", "_paymentState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/tourism/ui/booking/PaymentUiState;", "paymentState", "Lkotlinx/coroutines/flow/StateFlow;", "getPaymentState", "()Lkotlinx/coroutines/flow/StateFlow;", "processPayment", "", "booking", "Lcom/example/tourism/data/model/Booking;", "method", "Lcom/example/tourism/data/model/PaymentMethod;", "app_debug"})
public final class PaymentViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.tourism.data.repository.PaymentRepository paymentRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.tourism.ui.booking.PaymentUiState> _paymentState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.tourism.ui.booking.PaymentUiState> paymentState = null;
    
    public PaymentViewModel(@org.jetbrains.annotations.NotNull()
    com.example.tourism.data.repository.PaymentRepository paymentRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.tourism.ui.booking.PaymentUiState> getPaymentState() {
        return null;
    }
    
    public final void processPayment(@org.jetbrains.annotations.NotNull()
    com.example.tourism.data.model.Booking booking, @org.jetbrains.annotations.NotNull()
    com.example.tourism.data.model.PaymentMethod method) {
    }
}