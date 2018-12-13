package com.dev999.maven.genertation;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;

import java.io.File;

/**
 * @author helecong
 * @date 2018/12/13
 */
public class MyBatisGeneratorMojoTest extends AbstractMojoTestCase {
    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        // required for mojo lookups to work
        super.setUp();
    }

    /**
     * @throws Exception
     */
    public void testMojoGoal() throws Exception
    {
        File testPom = new File( getBasedir(),
                "src/main/resources/generator/generatorConfig.xml" );

        MyBatisGeneratorMojo mojo = (MyBatisGeneratorMojo) lookupMojo( "generate", testPom );

        assertNotNull( mojo );
    }
}
