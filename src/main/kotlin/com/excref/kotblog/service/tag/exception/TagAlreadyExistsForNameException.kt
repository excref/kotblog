package com.excref.kotblog.service.tag.exception

/**
 * @author Arthur Asatryan
 * @since 6/4/17 3:30 PM
 */
data class TagAlreadyExistsForNameException(val name: String, override val message: String) : RuntimeException(message)