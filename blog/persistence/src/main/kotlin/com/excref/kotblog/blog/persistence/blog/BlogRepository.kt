package com.excref.kotblog.blog.persistence.blog

import com.excref.kotblog.blog.service.blog.domain.Blog
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Arthur Asatryan
 * @since 6/10/17 7:21 PM
 */
@Repository
interface BlogRepository : CrudRepository<Blog, Long> {
    /**
     * Finds blog by uuid
     *
     * @param   uuid The blog uuid
     * @return  Blog if exists, null otherwise
     */
    fun findByUuid(uuid: String): Blog?

    /**
     * Finds blog name
     *
     * @param   name The blog name
     * @return  Blog if exists, null otherwise
     */
    fun findByName(name: String): Blog?
}