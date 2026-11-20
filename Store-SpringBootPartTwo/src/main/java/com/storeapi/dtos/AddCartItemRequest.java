package com.storeapi.dtos;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
public record AddCartItemRequest(@NotNull @Positive Long productId) {}
