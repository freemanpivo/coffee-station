package io.github.freemanpivo.productservice.core.exception

import org.springframework.http.HttpStatus

class HttpErrorException {

    class BadRequestException(httpCode: HttpStatus = HttpStatus.BAD_REQUEST, exception: BaseException)
    class ForbiddenException(httpCode: HttpStatus = HttpStatus.FORBIDDEN, exception: BaseException)

}