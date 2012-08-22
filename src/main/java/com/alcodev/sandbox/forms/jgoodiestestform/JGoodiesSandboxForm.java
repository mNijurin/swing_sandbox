package com.alcodev.sandbox.forms.jgoodiestestform;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;

/**
 * User: mnijurin
 * Date: 8/21/12
 * Time: 5:39 PM
 */
public class JGoodiesSandboxForm {

    private JPanel contentPanel;
    private JTextField companyField;
    private JTextField contactField;
    private JTextField ptiField;
    private JTextField powerField;
    private JTextField radiusField;
    private JTextField diameterField;

    public JPanel getContentPanel() {
        FormLayout layout = new FormLayout(
                "right:pref, 3dlu, pref, 7dlu, right:pref, 3dlu, pref", // columns
                "p, 3dlu, p, 3dlu, p, 9dlu, p, 3dlu, p, 3dlu, p");      // rows
        layout.setColumnGroups(new int[][]{{1, 5}, {3, 7}});

        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();

        CellConstraints constraints = new CellConstraints();

        builder.addSeparator("General", constraints.xyw(1, 1, 7));
        builder.addLabel("Company", constraints.xy(1, 3));
        builder.add(companyField, constraints.xyw(3, 3, 5));
        builder.addLabel("Contact", constraints.xy(1, 5));
        builder.add(contactField, constraints.xyw(3,5,5));

        builder.addSeparator("Propellerhead", constraints.xyw(1,7,7));
        builder.addLabel("PTI [kW]", constraints.xy(1,9));
        builder.add(ptiField, constraints.xy(3,9));
        builder.addLabel("Power [kW]", constraints.xy(5, 9));
        builder.add(powerField, constraints.xy(7, 9));
        builder.addLabel("R [mm]", constraints.xy (1, 11));
        builder.add(radiusField, constraints.xy(3, 11));
        builder.addLabel("D [mm]", constraints.xy (5, 11));
        builder.add(diameterField, constraints.xy(7, 11));

        return builder.getPanel();
//        return contentPanel;
    }
}
