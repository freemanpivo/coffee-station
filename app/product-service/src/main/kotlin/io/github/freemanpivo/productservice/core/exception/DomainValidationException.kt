package io.github.freemanpivo.productservice.core.exception

class DomainValidationException(code:String, message:String, fields:List<String>): BaseException(code, message, fields) {}