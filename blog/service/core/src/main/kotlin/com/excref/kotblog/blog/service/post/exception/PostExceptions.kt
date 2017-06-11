package com.excref.kotblog.blog.service.category.exception


data class PostNotExistsForUuidException(val uuid: String, override val message: String) : RuntimeException(message)