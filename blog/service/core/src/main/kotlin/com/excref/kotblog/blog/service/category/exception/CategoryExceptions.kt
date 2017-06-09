package com.excref.kotblog.blog.service.category.exception

/**
 * @author Ruben Vardanyan
 * @since 06/07/2017 12:39
 */

data class CategoryAlreadyExistsForNameAndUserException(val name: String, override val message: String) : RuntimeException(message)

data class CategoryNotExistsForUuidException(val uuid: String, override val message: String) : RuntimeException(message)