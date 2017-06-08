package com.excref.kotblog.blog.service.test.helper

import com.excref.kotblog.blog.service.category.domain.Category
import com.excref.kotblog.blog.service.tag.domain.Tag
import java.util.*

/**
 * @author Arthur Asatryan
 * @since 6/4/17 4:30 PM
 */
class ServiceImplTestHelper {
    //region Public methods
    fun buildTag(name: String = UUID.randomUUID().toString()): Tag = Tag(name)

    fun buildCategory(name: String = UUID.randomUUID().toString()): Category = Category(name)
    //endregion
}