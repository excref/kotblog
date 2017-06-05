package com.excref.kotblog.blog.service.tag.exception

/**
 * @author Arthur Asatryan
 * @since 6/6/17 12:16 AM
 */
data class TagAlreadyExistsForNameException(val name: String, override val message: String) : RuntimeException(message)

data class TagNotExistsForUuidException(val uuid: String, override val message: String) : RuntimeException(message)