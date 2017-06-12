package com.excref.kotblog.blog.persistence.post

import com.excref.kotblog.blog.service.post.domain.Post
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Created by Android Studio.
 * User: Gurgen Arustamyan
 * Date: 6/8/17
 * Time: 3:45 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
interface PostRepository : CrudRepository<Post, Long> {

    /**
     * Finds post by uuid
     *
     * @param uuid The post uuid
     * @return null if not found
     */
    fun findByUuid(uuid: String) : Post?
}