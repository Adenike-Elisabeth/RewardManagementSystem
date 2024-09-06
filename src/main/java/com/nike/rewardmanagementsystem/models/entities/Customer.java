package com.nike.rewardmanagementsystem.models.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long customerId;

    private BigDecimal totalCashback;

    private BigDecimal currentBalance;

   @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<CashbackTransaction> cashbackHistory;
}
