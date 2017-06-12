package com.excref.kotblog.blog.service.post.exception

/**
 * Created by Android Studio.
 * User: Gurgen Arustamyan
 * Date: 6/8/17
 * Time: 3:19 PM
 * To change this template use File | Settings | File Templates.
 */

data class PostWithEmptyContentException(val content: String, override val message: String) : RuntimeException(message)

data class PostNotExistsForUuidException(val uuid: String, override val message: String) : RuntimeException(message)