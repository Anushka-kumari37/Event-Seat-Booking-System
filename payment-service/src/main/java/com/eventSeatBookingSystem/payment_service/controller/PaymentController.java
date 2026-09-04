package com.eventSeatBookingSystem.payment_service.controller;

import com.eventSeatBookingSystem.payment_service.dto.PaymentRequestDto;
import com.eventSeatBookingSystem.payment_service.dto.PaymentResponseDto;
import com.eventSeatBookingSystem.payment_service.entity.PaymentStatus;
import com.eventSeatBookingSystem.payment_service.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody PaymentRequestDto paymentRequestDto) {
        PaymentResponseDto responseDto = paymentService.createPayment(paymentRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> getAllPayments() {
        List<PaymentResponseDto> responseDto = paymentService.getAllPayments();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> getPaymentById(@PathVariable Long id) {
        PaymentResponseDto responseDto = paymentService.getPaymentById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/booking/{id}")
    public  ResponseEntity<List<PaymentResponseDto>> getPaymentsByBookingId(@PathVariable Long bookingId) {
        List<PaymentResponseDto> responseDto = paymentService.getPaymentByBooking(bookingId);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/status")
    public  ResponseEntity<List<PaymentResponseDto>> getPaymentStatus(@RequestParam PaymentStatus paymentStatus) {
        List<PaymentResponseDto> responseDto = paymentService.getPaymentByStatus( paymentStatus);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<PaymentResponseDto> updatePaymentStatus(@PathVariable Long id,
                                                            @RequestBody PaymentRequestDto paymentRequestDto) {
        PaymentResponseDto responseDto = paymentService.updatePaymentStatus(id,paymentRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/refund/{id}")
    public ResponseEntity<PaymentResponseDto> refundPayment(@PathVariable Long id,
                                                            @RequestBody PaymentRequestDto paymentRequestDto) {
        PaymentResponseDto responseDto = paymentService.refundPayment(id, paymentRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/failed/{id}")
    public ResponseEntity<PaymentResponseDto> failedPayment(@PathVariable Long id,
                                                            @RequestBody PaymentRequestDto paymentRequestDto) {
        PaymentResponseDto responseDto = paymentService.failedPayment(id, paymentRequestDto);
        return ResponseEntity.ok(responseDto);
    }
}

