/* 
 * LoadFolderType.java
 * 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2009–2012 Steinbeis Forschungszentrum (STZ Ölbronn),
 * Copyright (c) 2006–2012 by Michael Hoffer
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
 * First, the following text must be displayed on the Canvas:
 * "based on VRL source code". In this case the VRL canvas icon must be removed.
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
 * Computing and Visualization in Science, 2011, in press.
 */

package vrl.types;

import vrl.annotation.TypeInfo;
import vrl.dialogs.FileDialogManager;
import vrl.reflection.LayoutType;
import vrl.reflection.TypeRepresentationBase;
import vrl.visual.VBoxLayout;
import vrl.visual.VTextField;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import vrl.lang.VLangUtils;

/**
 * TypeRepresentation for
 * <code>Java.io.File</code>.
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
@TypeInfo(type = File.class, input = true, output = false, style = "load-folder-dialog")
public class LoadFolderType extends TypeRepresentationBase {

    private static final long serialVersionUID = 500442740078037574L;
    private VTextField input;
    private File file;

    /**
     * Constructor.
     */
    public LoadFolderType() {
        VBoxLayout layout = new VBoxLayout(this, VBoxLayout.Y_AXIS);

        setLayout(layout);

        setLayoutType(LayoutType.STATIC);

        nameLabel.setText("Folder Name:");
        nameLabel.setAlignmentX(0.0f);

        this.add(nameLabel);

        input = new VTextField(this, "");

        setInputDocument(input, input.getDocument());

        int height = (int) this.input.getMinimumSize().getHeight();
        this.input.setSize(new Dimension(120, height));
        this.input.setMinimumSize(new Dimension(120, height));
        this.input.setMaximumSize(new Dimension(120, height));
        this.input.setPreferredSize(new Dimension(120, height));


        input.setAlignmentY(0.5f);
        input.setAlignmentX(0.0f);

        this.add(input);

        final FileDialogManager fileManager = new FileDialogManager();

        JButton button = new JButton("...");

        button.setMaximumSize(new Dimension(50,
                button.getMinimumSize().height));

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                File directory = null;
                if (getViewValueWithoutValidation() != null) {
                    directory = new File(
                            getViewValueWithoutValidation().
                            toString());
                    if (!directory.isDirectory()) {
                        directory = directory.getParentFile();
                    }
                }
                file = fileManager.getLoadFolder(getMainCanvas(),
                        directory, false);
                if (file != null) {
                    input.setText(file.toString());
                    input.setCaretPosition(input.getText().length());
                    input.setToolTipText(file.toString());
                }
            }
        });

        this.add(button);
    }

    @Override
    public void setViewValue(Object o) {
        input.setText(o.toString());
        input.setCaretPosition(input.getText().length());
        input.setToolTipText(o.toString());
    }

    @Override
    public Object getViewValue() {
        Object o = null;

        String inputText = input.getText();
        if (inputText.length() > 0) {
            try {
                o = new File(inputText);
            } catch (Exception e) {
                invalidateValue();
            }
        }

        return o;
    }

    @Override
    public void emptyView() {
        input.setText("");
    }

    @Override
    public String getValueAsCode() {

        Object v = getValue();

        if (v == null) {
            return "null";
        }

        File f = (File) v;

        return "new File(\"" + VLangUtils.addEscapesToCode(f.getPath()) + "\")";
    }
}
