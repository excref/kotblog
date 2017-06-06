package com.excref.kotblog.blog.persistence.tag

import com.excref.kotblog.blog.service.tag.domain.Tag
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Arthur Asatryan
 * @since 6/4/17 3:30 PM
 */
@Repository
interface TagRepository : CrudRepository<Tag, Long> {
    /**
     * Finds tag by name
     *
     * @param   name The tag name
     * @return  null if not found
     */
    fun findByName(name: String): Tag?

    /**
     * Finds tag by uuid
     *
     * @param   uuid The tag uuid
     * @return  null if not found
     */
    fun findByUuid(uuid: String): Tag?
}