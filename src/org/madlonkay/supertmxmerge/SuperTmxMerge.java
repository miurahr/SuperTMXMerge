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

import java.util.logging.Logger;
import org.madlonkay.supertmxmerge.gui.FileSelectWindow;

/**
 *
 * @author Aaron Madlon-Kay <aaron@madlon-kay.com>
 */
public class SuperTmxMerge {
    
    public static final Logger LOGGER = Logger.getLogger(SuperTmxMerge.class.getName());
    
    public static void diff(String file1, String file2) {
        DiffController controller = new DiffController();
        controller.setFile1(file1);
        controller.setFile2(file2);
        controller.go();
    }
    
    public static void merge(String baseFile, String file1, String file2) {
        MergeController controller = new MergeController();
        controller.setBaseFile(baseFile);
        controller.setLeftFile(file1);
        controller.setRightFile(file2);
        controller.go();
    }
    
    public static void promptForFiles() {
        FileSelectWindow window = new FileSelectWindow();
        window.setLocationRelativeTo(null);
        window.pack();
        window.setVisible(true);
    }
    
}
