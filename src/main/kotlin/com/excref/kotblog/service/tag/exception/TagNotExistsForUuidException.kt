package com.excref.kotblog.service.tag.exception

/**
 * @author Arthur Asatryan
 * @since 6/4/17 3:30 PM
 */
data class TagNotExistsForUuidException(val uuid: String, override val message: String) : RuntimeException(message)