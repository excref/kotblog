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
     * Finds category by name and by user userUuid
     *
     * @param   name The category name
     * @param   userUuid The user userUuid
     * @return  null if not found
     */

    fun findByNameAndUserUuid(name: String, userUuid: String): Category?
    /**
     * Finds category by uuid
     *
     * @param   uuid The category uuid
     * @return  null if not found
     */
    fun findByUuid(uuid: String): Category?
}