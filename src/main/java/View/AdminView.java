package View;

import Model.*;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;

import java.util.ArrayList;

public class AdminView extends JFrame {
    private JPanel mainPanel;
    private JTable casierTable;
    private JTable specTable;
    private JButton newSpectacolButton;
    private JButton newCasierButton;
    private JButton newAdminButton;
    private JButton profileButton;
    private JTextField nameCasierTF;
    private JTextField emailCasierTF;
    private JPasswordField passCasierTF;
    private JButton deleteCasierButton;
    private JButton updateCasierButton;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel midPanel;
    private JPanel bottomPanel;
    private JPanel bottomLeftPanel;
    private JPanel bottomRightPanel;
    private JPanel bottomLeftUpPanel;
    private JPanel bottomLeftUp1Panel;
    private JLabel nameCasierLabel;
    private JLabel emailCasierLabel;
    private JLabel passCasierLabel;
    private JTextField statusCasierTF;
    private JLabel statusCasierLabel;
    private JPanel bottomLeftUp2Panel;
    private JPanel bottomLeftDownPanel;
    private JButton deleteSpectacolButton;
    private JButton updateSpectacolButton;
    private JTextField genSpecTF;
    private JTextField dataSpecTF;
    private JTextField distributieSpecTF;
    private JTextField titluSpecTF;
    private JTextField regieSpecTF;
    private JTextField nrBileteSpecTF;
    private JPanel bottomRightUpPanel;
    private JPanel bottomRightUp1Panel;
    private JLabel genSpectacolLabel;
    private JLabel titluSpectacolLabel;
    private JLabel regieSpectacolLabel;
    private JLabel distSpectacolLabel;
    private JLabel dataSpectacolLabel;
    private JLabel nrBileteSpecLabel;
    private JPanel bottomRightUp2Panel;
    private JPanel bottomRightDownPanel;
    private JTextField idCasTF;
    private JLabel idCasierLabel;
    private JTextField idSpecTF;
    private JLabel idSpecLabel;


    public Admin admin;

    public AdminView()
    {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        casierTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int i = casierTable.getSelectedRow();
                TableModel model1 = casierTable.getModel();
                idCasTF.setText(model1.getValueAt(i,0).toString());
                nameCasierTF.setText(model1.getValueAt(i, 1).toString());
                emailCasierTF.setText(model1.getValueAt(i, 2).toString());
                passCasierTF.setText(model1.getValueAt(i, 3).toString());
                statusCasierTF.setText(model1.getValueAt(i,4).toString());
                System.out.println(model1.getValueAt(i,1).toString());
            }
        });

        specTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int i = specTable.getSelectedRow();
                TableModel model2 = specTable.getModel();
                idSpecTF.setText(model2.getValueAt(i,0).toString());
                genSpecTF.setText(model2.getValueAt(i,1).toString());
                titluSpecTF.setText(model2.getValueAt(i, 2).toString());
                nrBileteSpecTF.setText(model2.getValueAt(i, 6).toString());
                regieSpecTF.setText(model2.getValueAt(i, 3).toString());
                dataSpecTF.setText(model2.getValueAt(i,5).toString());
                distributieSpecTF.setText(model2.getValueAt(i,4).toString());
            }
        });

    }

    public void showCasiers()
    {
        ArrayList<Casier> list = CasierDAO.selectAll();
        DefaultTableModel model1 = new DefaultTableModel();
        model1.addColumn("ID");
        model1.addColumn("Name");
        model1.addColumn("Email");
        model1.addColumn("Password");
        model1.addColumn("Status");

       casierTable.setModel(model1);

        Object[] row = new Object[5];
        for ( int i=0; i<list.size(); i++ )
        {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getEmail();
            row[3] = list.get(i).getParola();
            row[4] = list.get(i).getStatus();
            model1.addRow(row);

        }
        casierTable.setModel(model1);
        model1.fireTableDataChanged();

    }


    public void showSpectacols()
    {
        ArrayList<Spectacol> list = SpectacolDAO.selectAll();
        DefaultTableModel model2 = new DefaultTableModel();

        model2.addColumn("ID");
        model2.addColumn("Gen");
        model2.addColumn("Titlu");
        model2.addColumn("Regie");
        model2.addColumn("Distributie");
        model2.addColumn("Data");
        model2.addColumn("Nr Bilete");

        specTable.setModel(model2);

        Object[] row = new Object[7];
        for ( int i=0; i<list.size(); i++ )
        {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getGen();
            row[2] = list.get(i).getTitlu();
            row[3] = list.get(i).getRegie();
            row[4] = list.get(i).getDistributie();
            row[5] = list.get(i).getData();
            row[6] = list.get(i).getNrBilete();
            model2.addRow(row);

        }
        specTable.setModel(model2);
        model2.fireTableDataChanged();

    }


    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin()
    {
        return this.admin;
    }

    public JTextField getIdSpecTF() {
        return idSpecTF;
    }

    public JTextField getIdCasTF() {
        return idCasTF;
    }

    public JTextField getNameCasierTF() {
        return nameCasierTF;
    }

    public JTextField getEmailCasierTF() {
        return emailCasierTF;
    }

    public JPasswordField getPassCasierTF() {
        return passCasierTF;
    }

    public JTextField getStatusCasierTF() {
        return statusCasierTF;
    }

    public JTextField getGenSpecTF() {
        return genSpecTF;
    }

    public JTextField getDataSpecTF() {
        return dataSpecTF;
    }

    public JTextField getDistributieSpecTF() {
        return distributieSpecTF;
    }

    public JTextField getTitluSpecTF() {
        return titluSpecTF;
    }

    public JTextField getRegieSpecTF() {
        return regieSpecTF;
    }

    public JTextField getNrBileteSpecTF() {
        return nrBileteSpecTF;
    }

    public void setNewCasierButton(ActionListener e)
    {
        this.newCasierButton.addActionListener(e);
    }

    public void setNewSpectacolButton(ActionListener e)
    {
        this.newSpectacolButton.addActionListener(e);
    }

    public void setNewAdminButton(ActionListener e)
    {
        this.newAdminButton.addActionListener(e);
    }
    public void setProfileButton(ActionListener e)
    {
        this.profileButton.addActionListener(e);
    }
    public void setDeleteCasierButton(ActionListener e)
    {
        this.deleteCasierButton.addActionListener(e);
    }
    public void setUpdateCasierButton(ActionListener e)
    {
        this.updateCasierButton.addActionListener(e);
    }
    public void setDeleteSpectacolButton(ActionListener e)
    {
        this.deleteSpectacolButton.addActionListener(e);
    }
    public void setUpdateSpectacolButton(ActionListener e)
    {
        this.updateSpectacolButton.addActionListener(e);
    }
}
