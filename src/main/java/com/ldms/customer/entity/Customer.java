package com.ldms.customer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

import static com.ldms.customer.constant.CustomerConstants.*;


@Entity
@Table(name = "Customer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = MANDATORY_CUSTOMER_ID)
    @Positive(message = LENGTH_CUSTOMER_ID)
    private Long customerId;
    @Column(length = 50)
    @NotBlank(message = NAME_IS_MANDATORY)
    private String name;
    @NotNull(message = BIRTH_IS_MANDATORY)
    private LocalDateTime dateOfBirth;
    @Column(length = 10)
    private String title;

}
