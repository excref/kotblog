package com.excref.kotblog.blog.service.blog

import com.excref.kotblog.blog.service.blog.domain.Blog

/**
 * @author Arthur Asatryan
 * @since 6/10/17 7:21 PM
 */
interface BlogService {
    /**
     * Gets blog by uuid
     *
     * @param   uuid The blog's uuid
     * @return  Blog
     * @throws  com.excref.kotblog.blog.service.blog.exception.BlogNotFoundForUuidException When blog not found
     */
    fun getByUuid(uuid: String): Blog

    /**
     * Checks if blog exists for given name
     *
     * @param   name The blog's name
     * @return  Boolean true if found, false otherwise
     */
    fun existsForName(name: String): Boolean

    /**
     * Creates a new blog
     *
     * @param   name The blog's name
     * @param   userUuid The user's uuid
     * @return  Blog if created successfully
     * @throws  com.excref.kotblog.blog.service.blog.exception.BlogAlreadyExistsForNameException If blog already exists
     */
    fun create(name: String, userUuid: String): Blog
}