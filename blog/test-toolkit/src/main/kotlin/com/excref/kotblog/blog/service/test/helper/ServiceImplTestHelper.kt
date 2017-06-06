package com.excref.kotblog.blog.service.test.helper

import com.excref.kotblog.blog.service.tag.domain.Tag
import java.util.*

/**
 * @author Arthur Asatryan
 * @since 6/4/17 4:30 PM
 */
class ServiceImplTestHelper {
    //region Public methods

    //region Tag
    fun buildTag(): Tag = buildTag(UUID.randomUUID().toString())

    fun buildTag(name: String): Tag = Tag(name)
    //endregion

    //region User
    //endregion

    //endregion
}