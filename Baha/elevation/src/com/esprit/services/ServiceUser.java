package com.esprit.services;

import com.esprit.models.User;

import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author NADHIR
 */
//
public class ServiceUser implements IserviceUser<User> {

    Connection cnx = (Connection) DataSource.getInstance().getCnx();

    public void ajouterUserEleve(User c) {

        try {
//            String nom, String prenom, String mdp, String email, String emailParent, int numeroParent, String sexe, String classe, int niveau, Date Datedenaissance, String status
            String requete = "INSERT INTO user (nom, prenom, mdp,email, emailParent, numeroParent, sexe, classe, niveau, datenaissance,status,role,image) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getPrenom());
            pst.setString(3, c.getMdp());
            pst.setString(4, c.getEmail());
            pst.setString(5, c.getEmailParent());
            pst.setInt(6, c.getNumeroParent());
            pst.setString(7, c.getSexe());
            pst.setString(8, c.getClasse());
            pst.setInt(9, c.getNiveau());
            pst.setDate(10, (Date) c.getDatedenaissance());
            pst.setString(11, "En Attente");
            pst.setString(12, "Eleve");
            pst.setString(13, c.getImage());

            pst.executeUpdate();
            System.out.println("Eleve Ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void ajouterUserEnseignant(User c) {

        try {
//    public User(String nom, String prenom, String mdp, String email, String sexe, int numero, Date Datedenaissance, String matiere, String status) {
            String requete = "INSERT INTO user (nom, prenom, mdp,email, sexe, numero, datenaissance,matiere,status,role,image) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getPrenom());
            pst.setString(3, c.getMdp());
            pst.setString(4, c.getEmail());
            pst.setString(5, c.getSexe());
            pst.setInt(6, c.getNumero());
            pst.setDate(7, (Date) c.getDatedenaissance());
            pst.setString(8, c.getMatiere());
            pst.setString(9, "En Attente");
            pst.setString(10, "Enseignant");
            pst.setString(11, c.getImage());

            pst.executeUpdate();
            System.out.println("Enseignant Ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
 public String getRole(String email) throws SQLException {
        String Query = "Select role from user where email=?";
        PreparedStatement prd = cnx.prepareStatement(Query);
        prd.setString(1, email);
        ResultSet res = prd.executeQuery();
        String value = "";
        while (res.next()) {
            value = res.getString(1);
            System.out.println(value);

        }
        return value;
    }
}
