package com.excref.kotblog.blog.service.test

import com.excref.kotblog.blog.service.test.helper.ServiceIntegrationTestHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests

/**
 * @author Arthur Asatryan
 * @since 6/4/17 4:09 PM
 */
@DataJpaTest
@EntityScan(basePackages = arrayOf("com.excref.kotblog.blog.service"))
@ContextConfiguration("classpath:applicationContext-service.xml")
abstract class AbstractServiceIntegrationTest : AbstractTransactionalJUnit4SpringContextTests() {

    //region Dependencies
    @Autowired
    protected lateinit var helper: ServiceIntegrationTestHelper
    //endregion
}