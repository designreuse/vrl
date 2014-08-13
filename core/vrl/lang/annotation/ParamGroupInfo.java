/* 
 * ParamGroupInfo.java
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

package vrl.lang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation to group type representations. The \ref annotation_page
 * shows how to use this annotation.
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 * @see MethodInfo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ParamGroupInfo {

    /**
     * Defines the group names, whether to expand the group(s) and also the
     * description(s) of the group(s).
     * 
     * Groups are separated with "<code>;</code>". Group properties 
     * (name, expand and description) are separated with "<code>|</code>".
     *
     * <p><b>String Format:</b></p>
     * <pre>
     *
     * id     = String
     * expand = Boolean
     * desc   = String
     *
     * format: id|expand|desc;id2|expand2|desc2
     *
     *         |___________|    |___________|
     *               |                |
     *            Group 1         SubGroup 1
     * </pre> <p> <b>Example:</b> </p>
     *
     * <pre>
     *
     * &#64;ParamGroupInfo(group="Group|true|my description;SubGroup|true|my other description")
     * </pre>
     * 
     * <p><b>Note: </b></p> <p> group properties <code>expand</code> and <code>desc</code>
     * are only evaluated once. Thus, only specify them in the first group info. 
     * All other occurances will be silently ignored.
     * </p>
     *
     * <p><b>Limitations: </b></p><p>the characters "|" and ";" must not be used
     * in group names or descriptions.</p>
     *
     */
    String group() default "default|true|no description";
}
