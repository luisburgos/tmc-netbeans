package fi.helsinki.cs.tmc.ui;

import fi.helsinki.cs.tmc.core.TmcCore;
import fi.helsinki.cs.tmc.core.domain.Organization;
import fi.helsinki.cs.tmc.core.domain.ProgressObserver;
import fi.helsinki.cs.tmc.core.holders.TmcSettingsHolder;

import com.google.common.base.Optional;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import org.openide.util.Exceptions;

public class OrganizationListWindow extends JPanel {

    private static JFrame frame;
    private final JList<OrganizationCard> organizations;
    private static JButton button;

    public OrganizationListWindow(List<Organization> organizations) {
        OrganizationCard[] organizationCards = new OrganizationCard[organizations.size()];
        for (int i = 0; i < organizations.size(); i++) {
            organizationCards[i] = new OrganizationCard(organizations.get(i));
        }
        this.organizations = new JList<>(organizationCards);
        this.organizations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.button = new JButton("Select");
        button.addActionListener(new SelectOrganizationListener(this));

        this.organizations.setCellRenderer(new OrganizationCellRenderer());
        this.organizations.setVisibleRowCount(4);
        JScrollPane pane = new JScrollPane(this.organizations);
        Dimension d = pane.getPreferredSize();
        d.width = 400;
        pane.setPreferredSize(d);
        pane.setBorder(new EmptyBorder(5, 0, 5, 0));
        this.organizations.setBackground(new Color(242, 241, 240));

        this.organizations.setSelectedIndex(setDefaultSelectedIndex());
        
        this.organizations.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() >= 2) {
                    button.doClick();
                }
            }
        });
        
        add(pane);
        add(button);
    }

    public static void display() throws Exception {
        if (frame == null) {
            frame = new JFrame("Select an organization");
        }
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        List<Organization> organizations = TmcCore.get().getOrganizations(ProgressObserver.NULL_OBSERVER).call();
        final OrganizationListWindow organizationListWindow = new OrganizationListWindow(organizations);
        frame.setContentPane(organizationListWindow);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        button.setMinimumSize(new Dimension(organizationListWindow.getWidth(), button.getHeight()));
        button.setMaximumSize(new Dimension(organizationListWindow.getWidth(), button.getHeight()));
        organizationListWindow.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent event) {
                button.setMinimumSize(new Dimension(organizationListWindow.getWidth(), button.getHeight()));
                button.setMaximumSize(new Dimension(organizationListWindow.getWidth(), button.getHeight()));
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });
    }
    
    public static boolean isWindowVisible() {
        if (frame == null) {
            return false;
        }
        return frame.isVisible();
    }

    private int setDefaultSelectedIndex() {
        Optional<Organization> selectedOrganization = TmcSettingsHolder.get().getOrganization();
        if (!selectedOrganization.isPresent()) {
            return 0;
        }
        final ListModel<OrganizationCard> list = organizations.getModel();
        for (int i = 0; i < list.getSize(); i++) {
            if (list.getElementAt(i).getOrganization().getName().equals(selectedOrganization.get().getName())) {
                return i;
            }
        }
        return 0;
    }

    class SelectOrganizationListener implements ActionListener {

        public SelectOrganizationListener(OrganizationListWindow window) {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            final OrganizationCard organization = organizations.getSelectedValue();
            setColors(organization, Color.white, Color.black);
            frame.setVisible(false);
            frame.dispose();
            try {
                PreferencesPanel panel;
                if (PreferencesUIFactory.getInstance().getCurrentUI() == null) {
                    panel = (PreferencesPanel) PreferencesUIFactory.getInstance().createCurrentPreferencesUI();
                } else {
                    panel = (PreferencesPanel) PreferencesUIFactory.getInstance().getCurrentUI();
                }
                panel.setOrganization(organization);
                CourseListWindow.display(panel);
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

    private void setColors(OrganizationCard organization, Color background, Color foreground) {
        organization.setBackground(background);
        for (Component c : organization.getComponents()) {
            c.setForeground(foreground);
        }
    }
}

class OrganizationCellRenderer extends JLabel implements ListCellRenderer {

    private static final Color HIGHLIGHT_COLOR = new Color(240, 119, 70);

    public OrganizationCellRenderer() {
    }

    @Override
    public Component getListCellRendererComponent(final JList list,
            final Object value, final int index, final boolean isSelected,
            final boolean hasFocus) {
        OrganizationCard organization = (OrganizationCard) value;
        if (isSelected) {
            organization.setColors(Color.white, HIGHLIGHT_COLOR);
        } else {
            organization.setColors(new Color(76, 76, 76), Color.white);
        }
        return organization;
    }
}