package com.excref.kotblog.blog.service.test.helper

import com.excref.kotblog.blog.service.category.CategoryService
import com.excref.kotblog.blog.service.category.domain.Category
import com.excref.kotblog.blog.service.tag.TagService
import com.excref.kotblog.blog.service.tag.domain.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

/**
 * @author Arthur Asatryan
 * @since 6/4/17 4:31 PM
 */
@Component
class ServiceIntegrationTestHelper {

    //region Dependencies
    @Autowired
    private lateinit var tagService: TagService

    @Autowired
    private lateinit var categoryService: CategoryService
    //endregion

    //region Public methods
    fun persistTag(): Tag {
        return persistTag(UUID.randomUUID().toString())
    }

    fun persistTag(name: String): Tag {
        return tagService.create(name)
    }

    fun persistCategory() : Category {
        return persistCategory(UUID.randomUUID().toString())
    }

    fun persistCategory(name: String) : Category {
        return categoryService.create(name)
    }
    //endregion
}