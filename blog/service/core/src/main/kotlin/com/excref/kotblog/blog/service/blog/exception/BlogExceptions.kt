package com.excref.kotblog.blog.service.blog.exception

/**
 * @author Arthur Asatryan
 * @since 6/10/17 7:21 PM
 */
data class BlogNotFoundForUuidException(val uuid: String, override val message: String) : RuntimeException(message)

data class BlogAlreadyExistsForNameException(val name: String, override val message: String) : RuntimeException(message)