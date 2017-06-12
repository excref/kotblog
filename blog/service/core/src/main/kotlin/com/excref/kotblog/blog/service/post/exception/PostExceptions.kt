package com.excref.kotblog.blog.service.post.exception

/**
 * @author Arthur Asatryan
 * @since 6/12/17 6:58 PM
 */
data class PostNotFoundForUuidException(val uuid: String, override val message: String) : RuntimeException(message)