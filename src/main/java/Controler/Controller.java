package Controler;

import Model.*;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Date;
import java.util.List;

class Controller
{
    private LoginView login;
    private RegisterView register;
    private AdminView adminView;
    private AdminProfileView adminProfileView;
    private CasierView casierView;

    Controller(LoginView login, RegisterView register, AdminView adminView, AdminProfileView adminProfileView, CasierView casierView)
    {
        this.login = login;
        this.register = register;
        this.adminView = adminView;
        this.adminProfileView = adminProfileView;
        this.casierView = casierView;

        this.login.setVisible(true);
        this.login.setLoginButton(new ButtonLogin());
        this.login.setRegisterButton(new LoginButtonRegister());

        this.register.setRegisterButton(new RegisterButtonRegister() );

        this.adminView.setDeleteCasierButton(new DeleteCasier());
        this.adminView.setDeleteSpectacolButton(new DeleteSpectacol());
        this.adminView.setNewAdminButton(new NewAdmin());
        this.adminView.setNewCasierButton(new NewCasButton());
        this.adminView.setNewSpectacolButton(new NewSpecButton());
        this.adminView.setUpdateCasierButton(new UpdateCasier());
        this.adminView.setUpdateSpectacolButton(new UpdateSpectacol());
        this.adminView.setProfileButton(new ProfileAdmin());

        this.adminProfileView.setDeleteAdminButton(new DeleteAdminButton());
        this.adminProfileView.setUpdateAdminButton(new UpdateAdminButton());

        this.casierView.setAddBiletButton(new AddBiletButton());
        this.casierView.setExportCSVButton(new ExportCSVButton());
        this.casierView.setExportJSONButton(new ExportJSONButton());
    }

    public class AddBiletButton implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            int rand = Integer.parseInt( casierView.getRandTF().getText());
            int numar = Integer.parseInt( casierView.getNumarTF().getText());
            int idSpec = Integer.parseInt( casierView.getIdSpecTF().getText());

            Bilet bilet = new Bilet(rand, numar, idSpec);
            Spectacol spectacol = SpectacolDAO.findById(idSpec);
            int ok=1;
            List<Bilet> list = BiletDAO.selectAll(idSpec);
            for ( Bilet b:list )
            {
                if( b.getRand() == bilet.getRand() && b.getNumar() == bilet.getNumar() )
                    ok = 0;
            }

            if ( spectacol.getNrBilete() > 0  && ok == 1) {
                BiletDAO.insert(bilet);
                casierView.showBilets(idSpec);
                spectacol.setNrBilete(spectacol.getNrBilete()-1);
                SpectacolDAO.update(idSpec, spectacol);
                casierView.showSpectacols();
                JOptionPane.showMessageDialog(null, "Done!");
            }
            else
                JOptionPane.showMessageDialog(null,"No more tickets");

        }
    }

    public class ExportCSVButton implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            Integer idSpectacol=Integer.parseInt(casierView.getIdSpecTF().getText());


            BiletDAO.export(ExporterFactory.getExporter("Csv"),idSpectacol);
            JOptionPane.showMessageDialog(null,"Exportarea in formatul Csv a reusit!");

        }
    }

    public class ExportJSONButton implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            int idSpectacol=Integer.parseInt(casierView.getIdSpecTF().getText());


            BiletDAO.export(ExporterFactory.getExporter("Json"),idSpectacol);
            JOptionPane.showMessageDialog(null,"Exportarea in formatul Json a reusit!");


        }
    }

    public class ButtonLogin implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String email = login.getEmailTf().getText();
            String parola = String.valueOf(login.getPassTF().getPassword());
            String encryptedPass = Encript.sha256(parola);
            Admin admin = new Admin();
            Casier casier = new Casier();
            try {
                admin = AdminDAO.findByEmail(email);
            }catch (Exception e1)
            {
                JOptionPane.showMessageDialog(null,"Erorr admin");
            }

           try {
                casier = CasierDAO.findByEmail(email);
            }catch (Exception e1)
            {
                JOptionPane.showMessageDialog(null,"Erorr casier");
            }



            if (admin.getName() != null && admin.getParola().compareTo(encryptedPass) == 0) {
               // JOptionPane.showMessageDialog(null, "Admin logged in");
                adminView.setAdmin(admin);
                adminView.setVisible(true);
                adminView.showCasiers();
                adminView.showSpectacols();

            }
            if (casier.getName() != null && casier.getParola().compareTo(encryptedPass) == 0) {
               // JOptionPane.showMessageDialog(null, "Casier logged id");
                casierView.setVisible(true);
                casierView.setCasier(casier);
                casierView.showSpectacols();
            }

        }
    }

    public class LoginButtonRegister implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            register.setVisible(true);

        }
    }

    public class RegisterButtonRegister implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String name = register.getNameTF().getText();
            String email = register.getEmailTF().getText();
            String pass = String.valueOf(register.getPasswordField1().getPassword());

            Casier casier = new Casier(name,email, Encript.sha256(pass),"Active");
            CasierDAO.insert(casier);
            JOptionPane.showMessageDialog(null, "Done!");

        }
    }


    public class NewSpecButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

            String gen = adminView.getGenSpecTF().getText();
            String titlu = adminView.getTitluSpecTF().getText();
            String regie = adminView.getRegieSpecTF().getText();
            String distrib = adminView.getDistributieSpecTF().getText();
            Date data = Encript.StringToDate(adminView.getDataSpecTF().getText());
            int nr = Integer.parseInt(adminView.getNrBileteSpecTF().getText());

            Spectacol spectacol = new Spectacol(gen,titlu,regie,distrib,data,nr);


            SpectacolDAO.insert(spectacol);

            adminView.showSpectacols();
            JOptionPane.showMessageDialog(null, "Done!");

        }
    }
    public class NewCasButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String name = adminView.getNameCasierTF().getText();
            String email = adminView.getEmailCasierTF().getText();
            String pass = String.valueOf(adminView.getPassCasierTF().getPassword());
            String status = adminView.getStatusCasierTF().getText();

            Casier casier = new Casier(name,email,Encript.sha256(pass),status);
            CasierDAO.insert(casier);

            adminView.showCasiers();

            JOptionPane.showMessageDialog(null, "Done!");
        }
    }
    public class NewAdmin implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String name = adminView.getNameCasierTF().getText();
            String email = adminView.getEmailCasierTF().getText();
            String pass = String.valueOf(adminView.getPassCasierTF().getPassword());
            String status = adminView.getStatusCasierTF().getText();

            Admin admin = new Admin(name,email,Encript.sha256(pass),status);
            AdminDAO.insert(admin);

            adminView.showCasiers();

            JOptionPane.showMessageDialog(null, "Done!");

        }
    }
    public class ProfileAdmin implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Basarabia eroania");
            adminProfileView.setVisible(true);
            adminProfileView.setAdmin(adminView.getAdmin());

            adminProfileView.getNameTF().setText(adminView.getAdmin().getName());
            adminProfileView.getIdTF().setText(String.valueOf(adminView.getAdmin().getId()));
            adminProfileView.getEmailTF().setText(adminView.getAdmin().getEmail());
            adminProfileView.getPasswordField1().setText(adminView.getAdmin().getParola());
            adminProfileView.getStatTF().setText(adminView.getAdmin().getStatus());


        }
    }
    public class DeleteCasier implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            CasierDAO.delete(Integer.parseInt(adminView.getIdCasTF().getText()));
            adminView.showCasiers();
            JOptionPane.showMessageDialog(null, "Done!");

        }
    }
    public class UpdateCasier implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {

            String name = adminView.getNameCasierTF().getText();
            String email = adminView.getEmailCasierTF().getText();
            String pass = String.valueOf(adminView.getPassCasierTF().getPassword());
            String status = adminView.getStatusCasierTF().getText();

            Casier casier = new Casier(name,email,Encript.sha256(pass),status);

            casier.setId(CasierDAO.findById(Integer.parseInt(adminView.getIdCasTF().getText())).getId());
            CasierDAO.update(Integer.parseInt(adminView.getIdCasTF().getText()),casier);
            adminView.showCasiers();
            JOptionPane.showMessageDialog(null, "Done!");


        }
    }
    public class DeleteSpectacol implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            SpectacolDAO.delete(Integer.parseInt(adminView.getIdSpecTF().getText()));
            adminView.showSpectacols();
            JOptionPane.showMessageDialog(null, "Done!");

        }
    }
    public class UpdateSpectacol implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String gen = adminView.getGenSpecTF().getText();
            String titlu = adminView.getTitluSpecTF().getText();
            String regie = adminView.getRegieSpecTF().getText();
            String distrib = adminView.getDistributieSpecTF().getText();
            Date data = Encript.StringToDate(adminView.getDataSpecTF().getText());
            int nr = Integer.parseInt(adminView.getNrBileteSpecTF().getText());

            Spectacol spectacol = new Spectacol(gen,titlu,regie,distrib,data,nr);
            spectacol.setId(Integer.parseInt(adminView.getIdSpecTF().getText()));

            SpectacolDAO.update(Integer.parseInt(adminView.getIdSpecTF().getText()), spectacol);

            adminView.showSpectacols();

            JOptionPane.showMessageDialog(null, "Done!");

        }
    }

    public class DeleteAdminButton implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            AdminDAO.delete(adminProfileView.getAdmin().getId());
            JOptionPane.showMessageDialog(null, "Done!");
        }
    }

    public class UpdateAdminButton implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = adminProfileView.getNameTF().getText();
            String email = adminProfileView.getEmailTF().getText();
            String pass = String.valueOf(adminProfileView.getPasswordField1().getPassword());
            String status = adminProfileView.getStatTF().getText();

            Admin admin = new Admin(name,email,Encript.sha256(pass),status);
            admin.setId(adminProfileView.getAdmin().getId());
            AdminDAO.update(adminProfileView.getAdmin().getId(),admin);
            JOptionPane.showMessageDialog(null, "Done!");
        }
    }


}
