package com.excref.kotblog.blog.service.test.helper

import com.excref.kotblog.blog.service.category.CategoryService
import com.excref.kotblog.blog.service.category.domain.Category
import com.excref.kotblog.blog.service.tag.TagService
import com.excref.kotblog.blog.service.tag.domain.Tag
import com.excref.kotblog.blog.service.user.UserService
import com.excref.kotblog.blog.service.user.domain.User
import com.excref.kotblog.blog.service.user.domain.UserRole
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
  
    @Autowired
    private lateinit var userService: UserService
    //endregion

    //region Public methods

    //region Tag
    fun persistTag(name: String = UUID.randomUUID().toString()): Tag {
        return tagService.create(name)
    }
    //endregion
    
    //region Category
    fun persistCategory(name: String = UUID.randomUUID().toString(), user: User = persistUser()): Category {
        return categoryService.create(name, user.uuid)
    }
    //endregion

    //endregion

    //region User
    fun persistUser(
            email: String = UUID.randomUUID().toString(),
            password: String = UUID.randomUUID().toString(),
            role: UserRole = UserRole.GUEST
    ): User = userService.create(email, password, role)
    //endregion

    //endregion
}