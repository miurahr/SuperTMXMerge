/*
 * Copyright (C) 2013 Aaron Madlon-Kay <aaron@madlon-kay.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.madlonkay.supertmxmerge;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.madlonkay.supertmxmerge.data.DiffSet;
import org.madlonkay.supertmxmerge.data.JAXB.JAXBTmx;
import org.madlonkay.supertmxmerge.util.DiffUtil;

/**
 *
 * @author Aaron Madlon-Kay <aaron@madlon-kay.com>
 */
public class SuperTmxMergeTest {
    
    private static final boolean DO_GUI_TESTS = false;
    
    public SuperTmxMergeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testDiffGui() {
        File file1 = getFilePath("resources/base.tmx");
        File file2 = getFilePath("resources/left.tmx");
        if (DO_GUI_TESTS) SuperTmxMerge.diff(file1, file2);
    }

    @Test
    public void testMergeGui() {
        File baseFile = getFilePath("resources/base.tmx");
        File file1 = getFilePath("resources/left.tmx");
        File file2 = getFilePath("resources/right.tmx");
        if (DO_GUI_TESTS) SuperTmxMerge.merge(baseFile, file1, file2);
    }

    @Test
    public void testPromptForFilesGui() {
        if (DO_GUI_TESTS) SuperTmxMerge.promptForFiles();
    }
    
    @Test
    public void testDiffTo() throws Exception {
        File baseFile = getFilePath("resources/base.tmx");
        File file1 = getFilePath("resources/left.tmx");
        File outFile = new File(file1.getParentFile(), "output.tmx");
        SuperTmxMerge.diffTo(baseFile, file1, outFile);
        
        File goldFile = getFilePath("resources/gold/diffToGold.tmx");
        
        ensureEmptyDiff(outFile, goldFile);
        
        outFile.delete();
    }
    
    @Test
    public void testMergeTo() throws Exception {
        File baseFile = getFilePath("resources/base.tmx");
        File file1 = getFilePath("resources/left-no-conflict.tmx");
        File file2 = getFilePath("resources/right-no-conflict.tmx");
        File outFile = new File(file1.getParentFile(), "output.tmx");
        SuperTmxMerge.mergeTo(baseFile, file1, file2, outFile);
        
        File goldFile = getFilePath("resources/gold/mergeToGold.tmx");
        
        ensureEmptyDiff(outFile, goldFile);
        
        outFile.delete();
    }
    
    @Test
    public void testMergeBidirectional() throws Exception {
        File baseFile = getFilePath("resources/base.tmx");
        File file1 = getFilePath("resources/left-no-conflict.tmx");
        File file2 = getFilePath("resources/right-no-conflict.tmx");
        File outFile1 = new File(file1.getParentFile(), "output1.tmx");
        SuperTmxMerge.mergeTo(baseFile, file1, file2, outFile1);
        
        File outFile2 = new File(file1.getParentFile(), "output2.tmx");
        SuperTmxMerge.mergeTo(baseFile, file2, file1, outFile2);
                
        ensureEmptyDiff(outFile1, outFile2);
        
        outFile1.delete();
        outFile2.delete();
    }
    
    @Test
    public void testMergeBaseless() throws Exception {
        File baseFile = null;
        File file1 = getFilePath("resources/left-no-conflict.tmx");
        File file2 = getFilePath("resources/right-no-conflict-baseless.tmx");
        File outFile = new File(file1.getParentFile(), "output.tmx");
        SuperTmxMerge.mergeTo(baseFile, file1, file2, outFile);
        
        File goldFile = getFilePath("resources/gold/baselessMergeToGold.tmx");
                
        ensureEmptyDiff(outFile, goldFile);
        
        outFile.delete();
    }
    
    @Test
    public void testCombineTo() throws Exception {
        File file1 = getFilePath("resources/base-part1.tmx");
        File file2 = getFilePath("resources/base-part2.tmx");
        File file3 = getFilePath("resources/base-part3.tmx");
        File outFile = new File(file1.getParentFile(), "output.tmx");
        SuperTmxMerge.combineTo(outFile, file1, file2, file3);
        
        File goldFile = getFilePath("resources/base.tmx");
        ensureEmptyDiff(outFile, goldFile);
        
        outFile.delete();
    }
    
    private File getFilePath(String identifier) {
        return new File(getClass().getClassLoader().getResource(identifier).getFile());
    }
    
    private void ensureEmptyDiff(File file1, File file2) throws Exception {
        DiffSet diff = DiffUtil.generateDiffSet(new JAXBTmx(file1), new JAXBTmx(file2));
        assertTrue(diff.added.isEmpty());
        assertTrue(diff.deleted.isEmpty());
        assertTrue(diff.modified.isEmpty());
    }
    
    private boolean compareFileTextContent(File file1, File file2) throws IOException {
        String content1 = normalize(getFileTextContent(file1));
        String content2 = normalize(getFileTextContent(file2));
        
        return content1.equals(content2);
    }
    
    private String getFileTextContent(File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
    }
    
    private String normalize(String string) {
        return string.replace("\r\n", "\n");
    }
}