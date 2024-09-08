package com.nike.rewardmanagementsystem.models.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long customerId;

    private String customerName;

    private String customerEmail;

    private String password;

    private String role;

    private BigDecimal totalCashback;

    private BigDecimal currentBalance;

   @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<CashbackTransaction> cashbackHistory;
}
