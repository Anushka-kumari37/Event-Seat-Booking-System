package com.eventSeatBookingSystem.payment_service.controller;

import com.eventSeatBookingSystem.payment_service.dto.PaymentRequestDto;
import com.eventSeatBookingSystem.payment_service.dto.PaymentResponseDto;
import com.eventSeatBookingSystem.payment_service.entity.PaymentStatus;
import com.eventSeatBookingSystem.payment_service.service.PaymentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PaymentResponseDto> createPayment(@Valid @RequestBody PaymentRequestDto paymentRequestDto) {
        PaymentResponseDto responseDto = paymentService.createPayment(paymentRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PaymentResponseDto>> getAllPayments() {
        List<PaymentResponseDto> responseDto = paymentService.getAllPayments();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PaymentResponseDto> getPaymentById(@PathVariable Long id) {
        PaymentResponseDto responseDto = paymentService.getPaymentById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/booking/{bookingId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public  ResponseEntity<List<PaymentResponseDto>> getPaymentsByBookingId(@PathVariable Long bookingId) {
        List<PaymentResponseDto> responseDto = paymentService.getPaymentByBooking(bookingId);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/status")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<List<PaymentResponseDto>> getPaymentStatus(@RequestParam PaymentStatus paymentStatus) {
        List<PaymentResponseDto> responseDto = paymentService.getPaymentByStatus( paymentStatus);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PaymentResponseDto> updatePaymentStatus(@PathVariable Long id) {
        PaymentResponseDto responseDto = paymentService.updatePaymentStatus(id);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/refund/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PaymentResponseDto> refundPayment(@PathVariable Long id) {
        PaymentResponseDto responseDto = paymentService.refundPayment(id);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/failed/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PaymentResponseDto> failedPayment(@PathVariable Long id) {
        PaymentResponseDto responseDto = paymentService.failedPayment(id);
        return ResponseEntity.ok(responseDto);
    }
}

