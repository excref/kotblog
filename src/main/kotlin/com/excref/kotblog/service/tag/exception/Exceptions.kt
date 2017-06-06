package com.excref.kotblog.service.tag.exception

/**
 * Company: SFL LLC
 * Created on 6/6/2017
 *
 * @author Hovsep
 */

data class TagAlreadyExistsForNameException(val name: String, override val message: String) : RuntimeException(message)

data class TagNotExistsForUuidException(val uuid: String, override val message: String) : RuntimeException(message)