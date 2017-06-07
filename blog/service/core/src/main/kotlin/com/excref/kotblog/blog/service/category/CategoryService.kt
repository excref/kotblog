package com.excref.kotblog.blog.service.category

import com.excref.kotblog.blog.service.category.domain.Category

/**
 * @author Ruben Vardanyan
 * @since 06/07/2017 12:28
 */
interface CategoryService {
    /**
     * Creates a new category with the given name
     *
     * @param   name The category name
     * @return  Persistent category
     * @throws  com.excref.kotblog.blog.service.tag.exception.TagAlreadyExistsForNameException If the category already exists for the given name
     */
    fun create(name: String): Category

    /**
     * Gets category by given uuid
     *
     * @param   uuid The category uuid
     * @return  The category
     * @throws  com.excref.kotblog.blog.service.tag.exception.TagNotExistsForUuidException If category not exists
     */
    fun getByUuid(uuid: String): Category

    /**
     * Checks if the category exists for the given name
     *
     * @param   name The category name
     * @return  true if category exists, false otherwise
     */
    fun existsForName(name: String): Boolean
}