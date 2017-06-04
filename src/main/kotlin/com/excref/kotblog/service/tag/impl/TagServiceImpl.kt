package com.excref.kotblog.service.tag.impl

import com.excref.kotblog.domain.tag.Tag
import com.excref.kotblog.repository.tag.TagRepository
import com.excref.kotblog.service.tag.TagService
import com.excref.kotblog.service.tag.exception.TagAlreadyExistsForNameException
import com.excref.kotblog.service.tag.exception.TagNotExistsForUuidException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Arthur Asatryan
 * @since 6/4/17 3:20 PM
 */
@Service
class TagServiceImpl : TagService {

    private val logger: Logger = LoggerFactory.getLogger(TagServiceImpl::class.java)

    //region Dependencies
    @Autowired
    private lateinit var tagRepository: TagRepository
    //endregion

    //region Public methods
    @Transactional
    override fun create(name: String): Tag {
        assertTagNotExistForName(name)
        logger.debug("Creating tag with name - $name")
        return tagRepository.save(Tag(name))
    }

    @Transactional(readOnly = true)
    override fun getByUuid(uuid: String): Tag {
        val tag = tagRepository.findByUuid(uuid)
        assertTagNotNullForUuid(tag, uuid)
        return tag as Tag
    }

    @Transactional(readOnly = true)
    override fun existsForName(name: String): Boolean {
        return tagRepository.findByName(name) != null
    }
    //endregion

    //region Utility methods
    private fun assertTagNotExistForName(name: String) {
        if (existsForName(name)) {
            logger.error("The tag with name $name already exists")
            throw TagAlreadyExistsForNameException(name, "The tag with name $name already exists")
        }
    }

    private fun assertTagNotNullForUuid(tag: Tag?, uuid: String) {
        if (tag == null) {
            logger.error("Can not find tag for uuid $uuid")
            throw TagNotExistsForUuidException(uuid, "Can not find tag for uuid $uuid")
        }
    }
    //endregion
}