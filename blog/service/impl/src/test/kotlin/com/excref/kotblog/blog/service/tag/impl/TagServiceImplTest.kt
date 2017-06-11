package com.excref.kotblog.blog.service.tag.impl

import com.excref.kotblog.blog.persistence.tag.TagRepository
import com.excref.kotblog.blog.service.tag.TagService
import com.excref.kotblog.blog.service.tag.domain.Tag
import com.excref.kotblog.blog.service.tag.exception.TagAlreadyExistsForNameException
import com.excref.kotblog.blog.service.tag.exception.TagNotExistsForUuidException
import com.excref.kotblog.blog.service.tag.exception.TagsNotExistsForUuidsException
import com.excref.kotblog.blog.service.test.AbstractServiceImplTest
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.*
import org.easymock.Mock
import org.easymock.TestSubject
import org.junit.Test
import java.util.*

/**
 * @author Arthur Asatryan
 * @since 6/4/17 3:20 PM
 */
class TagServiceImplTest : AbstractServiceImplTest() {

    //region Test subject and mocks
    @TestSubject
    private val tagService: TagService = TagServiceImpl()

    @Mock
    private lateinit var tagRepository: TagRepository
    //endregion

    //region Test methods

    //region initial
    @Test
    fun testTagService() {
        assertThat(tagService).isNotNull()
    }

    @Test
    fun testTagRepository() {
        assertThat(tagRepository).isNotNull()
    }
    //endregion

    //region create
    /**
     * When tag with name already exists
     */
    @Test
    fun create1() {
        resetAll()
        // test data
        val name = "C++"
        val tag = helper.buildTag(name)
        // expectations
        expect(tagRepository.findByName(name)).andReturn(tag)
        replayAll()
        // test scenario
        try {
            tagService.create(name)
        } catch (ex: TagAlreadyExistsForNameException) {
            assertThat(ex).isNotNull().extracting("name").containsOnly(name)
        }
        verifyAll()
    }

    @Test
    fun create2() {
        resetAll()
        // test data
        val name = "Java"
        // expectations
        expect(tagRepository.findByName(name)).andReturn(null)
        expect(tagRepository.save(isA(Tag::class.java))).andAnswer({ getCurrentArguments()[0] as Tag? })
        replayAll()
        // test scenario
        val result = tagService.create(name)
        assertThat(result).isNotNull().extracting("name").containsOnly(name)
        verifyAll()
    }
    //endregion

    //region getByUuid
    /**
     * When tag does not exists
     */
    @Test
    fun testGetByUuid1() {
        resetAll()
        // test data
        val uuid = UUID.randomUUID().toString()
        // expectations
        expect(tagRepository.findByUuid(uuid)).andReturn(null)
        replayAll()
        // test scenario
        try {
            tagService.getByUuid(uuid)
        } catch (ex: TagNotExistsForUuidException) {
            assertThat(ex).isNotNull().extracting("uuid").containsOnly(uuid)
        }
        verifyAll()
    }

    /**
     * When tag exists
     */
    @Test
    fun testGetByUud2() {
        resetAll()
        // test data
        val tag = helper.buildTag()
        val uuid = tag.uuid
        // expectations
        expect(tagRepository.findByUuid(uuid)).andReturn(tag)
        replayAll()
        // test scenario
        val result = tagService.getByUuid(uuid)
        assertThat(result).isNotNull().isEqualTo(tag)
        verifyAll()
    }
    //endregion

    //region getByUuids
    /**
     * When no tags found
     */
    @Test
    fun testGetByUuids1() {
        resetAll()
        // test data
        val uuid = UUID.randomUUID().toString()
        val uuid2 = UUID.randomUUID().toString()
        val uuids = listOf(uuid, uuid2)
        // expectations
        expect(tagRepository.findByUuidIn(uuids)).andReturn(null)
        replayAll()
        // test scenario
        try {
            tagService.getByUuids(uuids)
        } catch (ex: TagsNotExistsForUuidsException) {
            assertThat(ex).isNotNull().extracting("uuids").containsOnly(uuids)
        }
        verifyAll()
    }

    /**
     * When tag exists
     */
    @Test
    fun testGetByUuds2() {
        resetAll()
        // test data
        val tag = helper.buildTag()
        val tag2 = helper.buildTag()
        val uuids = listOf<String>(tag.uuid, tag2.uuid)
        // expectations
        expect(tagRepository.findByUuidIn(uuids)).andReturn(listOf(tag, tag2))
        replayAll()
        // test scenario
        val result = tagService.getByUuids(uuids)
        assertThat(result).isNotNull().containsOnly(tag, tag2)
        verifyAll()
    }
    //endregion

    //region existsForName

    /**
     * When not exists
     */
    @Test
    fun testExistsForName1() {
        resetAll()
        // test data
        val name = "Rust"
        // expectations
        expect(tagRepository.findByName(name)).andReturn(null)
        replayAll()
        // test scenario
        assertThat(tagService.existsForName(name)).isFalse()
        verifyAll()
    }

    /**
     * When exists
     */
    @Test
    fun testExistsForName2() {
        resetAll()
        // test data
        val name = "Rust"
        // expectations
        val tag = Tag(name)
        expect(tagRepository.findByName(name)).andReturn(tag)
        replayAll()
        // test scenario
        assertThat(tagService.existsForName(name)).isTrue()
        verifyAll()
    }
    //endregion

    //endregion
}

