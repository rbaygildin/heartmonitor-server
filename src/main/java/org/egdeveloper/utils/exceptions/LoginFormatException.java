package org.egdeveloper.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Login or password have bad format")
public class LoginFormatException extends RuntimeException {
}
