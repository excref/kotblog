package com.excref.kotblog.service.tag.impl

import com.excref.kotblog.domain.tag.Tag
import com.excref.kotblog.repository.tag.TagRepository
import com.excref.kotblog.service.tag.TagService
import com.excref.kotblog.service.tag.exception.TagAlreadyExistsForNameException
import com.excref.kotblog.service.tag.exception.TagNotExistsForUuidException
import com.excref.kotblog.service.test.AbstractServiceImplTest
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
        assertThat(tagService.existsForName(name)).isFalse()
        verifyAll()
    }
    //endregion

    //endregion
}

