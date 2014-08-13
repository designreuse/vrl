/* 
 * ClassCodeExtractor.java
 *
 * Copyright (c) 2009–2014 Steinbeis Forschungszentrum (STZ Ölbronn),
 * Copyright (c) 2006–2014 by Michael Hoffer
 * 
 * This file is part of Visual Reflection Library (VRL).
 *
 * VRL is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * see: http://opensource.org/licenses/LGPL-3.0
 *      file://path/to/VRL/src/eu/mihosoft/vrl/resources/license/lgplv3.txt
 *
 * VRL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * This version of VRL includes copyright notice and attribution requirements.
 * According to the LGPL this information must be displayed even if you modify
 * the source code of VRL. Neither the VRL Canvas attribution icon nor any
 * copyright statement/attribution may be removed.
 *
 * Attribution Requirements:
 *
 * If you create derived work you must do three things regarding copyright
 * notice and author attribution.
 *
 * First, the following text must be displayed on the Canvas or an equivalent location:
 * "based on VRL source code".
 * 
 * Second, the copyright notice must remain. It must be reproduced in any
 * program that uses VRL.
 *
 * Third, add an additional notice, stating that you modified VRL. In addition
 * you must cite the publications listed below. A suitable notice might read
 * "VRL source code modified by YourName 2012".
 * 
 * Note, that these requirements are in full accordance with the LGPL v3
 * (see 7. Additional Terms, b).
 *
 * Publications:
 *
 * M. Hoffer, C.Poliwoda, G.Wittum. Visual Reflection Library -
 * A Framework for Declarative GUI Programming on the Java Platform.
 * Computing and Visualization in Science, in press.
 */

package vrl.lang.g4j;

import vrl.lang.Patterns;

/**
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
public class ClassCodeExtractor implements StringProcessor {

    private static final String id = "Processor:classcode";
    private TemplateClass clazz;

    public ClassCodeExtractor(TemplateClass clazz) {
        this.clazz = clazz;
    }

    public String process(String code) {

        // filter comments, chars and strings
        FilterComments fC = new FilterComments();
        code = fC.process(code);
        FilterChars fCh = new FilterChars();
        code = fCh.process(code);
        FilterStrings fS = new FilterStrings();
        code = fS.process(code);

        StringBuilder result = new StringBuilder();

        String[] lines = code.split("\n");

        boolean insideOfClassImplementation = false;
        int blockCount = 0;


        for (String l : lines) {

            if (!insideOfClassImplementation
                    && Patterns.TEMPLATE_CLS_HEADER.matcher(l).find()) {
                insideOfClassImplementation = 
                        LangUtils.classNameFromTemplateClsHeader(l).
                        equals(clazz.getName());
            }

            if (insideOfClassImplementation) {
                for (int i = 0; i < l.length(); i++) {

                    char ch = l.charAt(i);

                    if (ch=='{') {
                        blockCount++;
                    }
                    
                    if (ch=='}') {
                        blockCount--;
                    }
                }

                if (insideOfClassImplementation) {
                    result.append(l).append("\n");
                }

                if (blockCount == 0) {
                    break;
                }
            }
        }

        return result.toString();
    }

    public String getID() {
        return id;
    }
}
