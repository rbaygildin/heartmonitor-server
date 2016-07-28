package org.egdeveloper.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Security token not found")
public class SecurityTokenIlegalException extends RuntimeException{
}
