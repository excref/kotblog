package com.excref.kotblog.blog.service.user.exception

/**
 * @author Arthur Asatryan
 * @since 6/8/17 12:41 AM
 */
data class UserAlreadyExistsForEmailException(val email: String, override val message: String) : RuntimeException(message)

data class UserNotFoundForUuidException(val uuid: String, override val message: String) : RuntimeException(message)