package com.excref.kotblog.blog.persistence.category

import com.excref.kotblog.blog.service.category.domain.Category
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Ruben Vardanyan
 * @since 06/07/2017 12:33
 */
@Repository
interface CategoryRepository : CrudRepository<Category, Long> {
    /**
     * Finds tag by name
     *
     * @param   name The tag name
     * @return  null if not found
     */
    fun findByName(name: String): Category?

    /**
     * Finds tag by uuid
     *
     * @param   uuid The tag uuid
     * @return  null if not found
     */
    fun findByUuid(uuid: String): Category?
}