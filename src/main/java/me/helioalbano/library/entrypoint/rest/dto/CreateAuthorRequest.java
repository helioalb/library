package me.helioalbano.library.entrypoint.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAuthorRequest(
    @NotBlank(message = "cannot_be_blank")
    @Size(max = 100, message = "must_be_less_than_100")
    String fullName
) {
}
