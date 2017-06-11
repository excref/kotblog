package com.excref.kotblog.blog.service.tag

import com.excref.kotblog.blog.service.tag.domain.Tag

/**
 * @author Arthur Asatryan
 * @since 6/4/17 3:18 PM
 */
interface TagService {
    /**
     * Creates a new tag with the given name
     *
     * @param   name The tag name
     * @return  Persistent tag
     * @throws  com.excref.kotblog.blog.service.tag.exception.TagAlreadyExistsForNameException If the tag already exists for the given name
     */
    fun create(name: String): Tag

    /**
     * Gets tag by given uuid
     *
     * @param   uuid The tag uuid
     * @return  The tag
     * @throws  com.excref.kotblog.blog.service.tag.exception.TagNotExistsForUuidException If tag not exists
     */
    fun getByUuid(uuid: String): Tag

    /**
     * Gets tag by given uuid
     *
     * @param   uuids The tag uuid
     * @return  Tags
     * @throws  com.excref.kotblog.blog.service.tag.exception.TagNotExistsForUuidException If tag not exists
     */
    fun getByUuids(uuids: List<String>): List<Tag>

    /**
     * Checks if the tag exists for the given name
     *
     * @param   name The tag name
     * @return  true if tag exists, false otherwise
     */
    fun existsForName(name: String): Boolean
}