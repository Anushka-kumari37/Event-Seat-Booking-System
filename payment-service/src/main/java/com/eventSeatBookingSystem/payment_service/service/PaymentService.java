package com.eventSeatBookingSystem.payment_service.service;

import com.eventSeatBookingSystem.payment_service.client.BookingClient;
import com.eventSeatBookingSystem.payment_service.dto.BookingResponseDto;
import com.eventSeatBookingSystem.payment_service.dto.PaymentRequestDto;
import com.eventSeatBookingSystem.payment_service.dto.PaymentResponseDto;
import com.eventSeatBookingSystem.payment_service.entity.Payment;
import com.eventSeatBookingSystem.payment_service.entity.PaymentStatus;
import com.eventSeatBookingSystem.payment_service.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {

    private  final PaymentRepository paymentRepository;
    private final BookingClient bookingClient;

    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) {

        BookingResponseDto booking = bookingClient.getById(paymentRequestDto.getBookingId());
        Payment payment = new Payment();
        payment.setBookingId(booking.getId());
        payment.setUserId(booking.getUserId());
        payment.setAmount(booking.getTotalAmount());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentMethod(paymentRequestDto.getPaymentMethod());
        payment.setPaymentStatus(PaymentStatus.PENDING);

        Payment savedPayment = paymentRepository.save(payment);
        return ConvertToDto(savedPayment);
    }

    private PaymentResponseDto ConvertToDto(Payment savedPayment) {
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
        paymentResponseDto.setPaymentId(savedPayment.getId());
        paymentResponseDto.setUserId(savedPayment.getUserId());
        paymentResponseDto.setPaymentMethod(savedPayment.getPaymentMethod());
        paymentResponseDto.setPaymentStatus(savedPayment.getPaymentStatus());
        paymentResponseDto.setPaymentDate(savedPayment.getPaymentDate());
        paymentResponseDto.setBookingId(savedPayment.getBookingId());
        paymentResponseDto.setAmount(savedPayment.getAmount());

        return paymentResponseDto;
    }

    public List<PaymentResponseDto> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        if (payments.isEmpty()) {
            throw new RuntimeException("No payments found");
        }
        return payments.stream().map(this::ConvertToDto).toList();
    }


    public PaymentResponseDto getPaymentById(Long id) {
        Payment payment =  paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id " +id));
        return ConvertToDto(payment);
    }

    public List<PaymentResponseDto> getPaymentByBooking(Long bookingId) {
        List<Payment> payments = paymentRepository.findByBookingId(bookingId);
        if (payments.isEmpty()) {
            throw new RuntimeException("No payments found with this booking id " + bookingId);
        }
        return payments.stream().map(this::ConvertToDto).toList();
    }

    public PaymentResponseDto updatePaymentStatus(Long id, PaymentRequestDto paymentRequestDto) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id " +id));
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        Payment updatedPayment = paymentRepository.save(payment);
        return ConvertToDto(updatedPayment);
    }

    public PaymentResponseDto refundPayment(Long id, PaymentRequestDto paymentRequestDto) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id " +id));
        payment.setPaymentStatus(PaymentStatus.REFUNDED);
       Payment payment1=  paymentRepository.save(payment);
        return ConvertToDto(payment1);
    }

    public PaymentResponseDto failedPayment(Long id, PaymentRequestDto paymentRequestDto) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Payment not found with id " +id));
        payment.setPaymentStatus(PaymentStatus.FAILED);
        Payment payment1=  paymentRepository.save(payment);
        return ConvertToDto(payment1);
    }

    public List<PaymentResponseDto> getPaymentByStatus( PaymentStatus paymentStatus) {
        List<Payment> payments = paymentRepository.findByPaymentStatus(paymentStatus);
        if (payments.isEmpty()) {
            throw new RuntimeException("No payments found in this status " + paymentStatus);
        }
        return payments.stream().map(this::ConvertToDto).toList();
    }
}
