package com.niiran.engineering.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {

    @NotNull(message = "Customer Id is required")
    private Long customerId;

    @NotNull(message = "Product Id is required")
    private Long productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity should be at least 1")
    private Integer quantity;

    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount should be greater than zero")
    private Double amount;

}
