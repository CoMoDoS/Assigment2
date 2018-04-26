package View;

import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CasierView extends JFrame{
    private JPanel panel1;
    private JTable spectacoleTable;
    private JTable bileteTable;
    private JTextField idSpecTF;
    private JTextField randTF;
    private JTextField numarTF;
    private JButton addBiletButton;
    private JButton exportCSVButton;
    private JButton exportJSONButton;

    private Casier casier;

    public CasierView()
    {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();

        spectacoleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int i = spectacoleTable.getSelectedRow();
                TableModel model1 = spectacoleTable.getModel();
                idSpecTF.setText(model1.getValueAt(i,0).toString());
                showBilets(Integer.parseInt(idSpecTF.getText()));
            }
        });
    }

    public void showBilets(int id)
    {
        ArrayList<Bilet> list = BiletDAO.selectAll(id);
        DefaultTableModel model2 = new DefaultTableModel();

        model2.addColumn("ID");
        model2.addColumn("Rand");
        model2.addColumn("Numar");
        model2.addColumn("ID Soectacol");


        bileteTable.setModel(model2);

        Object[] row = new Object[5];
        for ( int i=0; i<list.size(); i++ )
        {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getRand();
            row[2] = list.get(i).getNumar();
            row[3] = list.get(i).getSpectacol_id();

            model2.addRow(row);

        }
        bileteTable.setModel(model2);
        model2.fireTableDataChanged();

    }

    public void showSpectacols()
    {
        ArrayList<Spectacol> list = SpectacolDAO.selectAll();
        DefaultTableModel model1 = new DefaultTableModel();

        model1.addColumn("ID");
        model1.addColumn("Gen");
        model1.addColumn("Titlu");
        model1.addColumn("Regie");
        model1.addColumn("Distributie");
        model1.addColumn("Data");
        model1.addColumn("Nr Bilete");

        spectacoleTable.setModel(model1);

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
            model1.addRow(row);

        }
        spectacoleTable.setModel(model1);
        model1.fireTableDataChanged();

    }

    public Casier getCasier() {
        return casier;
    }

    public void setCasier(Casier casier) {
        this.casier = casier;
    }

    public JTextField getIdSpecTF() {
        return idSpecTF;
    }

    public JTextField getRandTF() {
        return randTF;
    }

    public JTextField getNumarTF() {
        return numarTF;
    }

    public void setAddBiletButton(ActionListener e)
    {
        this.addBiletButton.addActionListener(e);
    }

    public void setExportCSVButton(ActionListener e)
    {
        this.exportCSVButton.addActionListener(e);
    }

    public void setExportJSONButton(ActionListener e)
    {
        this.exportJSONButton.addActionListener(e);
    }


}
