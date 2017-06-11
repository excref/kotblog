package com.excref.kotblog.blog.service.test.helper

import com.excref.kotblog.blog.service.blog.domain.Blog
import com.excref.kotblog.blog.service.category.domain.Category
import com.excref.kotblog.blog.service.tag.domain.Tag
import com.excref.kotblog.blog.service.user.domain.User
import com.excref.kotblog.blog.service.user.domain.UserRole
import java.util.*

/**
 * @author Arthur Asatryan
 * @since 6/4/17 4:30 PM
 */
class ServiceImplTestHelper {
    //region Public methods

    //region Tag
    fun buildTag(name: String = UUID.randomUUID().toString()): Tag = Tag(name)
    //endregion

    //region User
    fun buildUser(
            email: String = UUID.randomUUID().toString(),
            password: String = UUID.randomUUID().toString(),
            role: UserRole = UserRole.USER
    ): User = User(email, password, role)
    //endregion

    //region Category
    fun buildCategory(name: String = UUID.randomUUID().toString()): Category = Category(name)
    //endregion

    //region Blog
    fun buildBlog(name: String = UUID.randomUUID().toString(), user: User = buildUser()): Blog = Blog(name, user)
    //endregion

    //endregion
}