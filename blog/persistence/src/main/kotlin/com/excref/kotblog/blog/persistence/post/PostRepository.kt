package com.excref.kotblog.blog.persistence.post

import com.excref.kotblog.blog.service.post.domain.Post
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Rafayel Mirzoyan
 * @since 6/10/17 8:00 PM
 */
@Repository
interface PostRepository : CrudRepository<Post, Long> {

    /**
     * Finds Post by uuid
     *
     * @param   uuid The Post uuid
     * @return  Post if exists, null otherwise
     */
    fun findByUuid(uuid: String): Post?

    /**
     * Finds Post name
     *
     * @param   name The Post name
     * @return  Post if exists, null otherwise
     */
    fun findByName(name: String): List<Post>?
}