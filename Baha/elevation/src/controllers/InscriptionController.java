/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.inscription;
import Service.ServiceInscription;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * FXML Controller class
 *
 * @author infoplus
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField adresseM;
    @FXML
    private Button btn_inscription;
    @FXML
    private Button btn_annuler;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField idd;
    @FXML
    private Text nomexam;
    private int id_ex;
    private int heure;
    private String date;
    public void setNameExam(String nom_matiere,int id_examen,String date){
        
        this.nomexam.setText(nom_matiere);
        this.id_ex=id_examen;
        this.date=date;
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sinscrire(ActionEvent event) throws MessagingException, SQLException {
        inscription ie=new inscription();
        ie.setId_etud(Integer.parseInt(idd.getText()));
        ie.setId_ex(this.id_ex);
        ServiceInscription ies= ServiceInscription.getInscription();
        ies.insert(ie);
        
        Properties prop = System.getProperties();
        prop.put("mail.smtp.port", "587");
         prop.put("mail.smtp.auth", true);
         prop.put("mail.smtp.starttls.enable", "true");
        Session newSession = Session.getDefaultInstance(prop, null);

        String emailsubject="Elevation inscription valideé";
        String emailbody="Bienvenue Mr/Mme "+nom.getText()+" "+prenom.getText()+  
                ", votre demande d'inscription a été acceptée pour l'examen "+nomexam.getText()+"  "+
               "l'examen sera le "+this.date+ " ";
        Message message = new MimeMessage(newSession);
        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(adresseM.getText()));
        } catch (AddressException ex) {
           // Logger.getLogger(InscriptionEController.class.getName()).log(Level.SEVERE, null, ex);
        }

         message.setSubject(emailsubject);




         MimeBodyPart mimeBodyPart = new MimeBodyPart();
         mimeBodyPart.setContent(emailbody, "text/html");

          Multipart multipart = new MimeMultipart();
          multipart.addBodyPart(mimeBodyPart);


          message.setContent(multipart);

           String fromuser ="chbaha40@gmail.com";
           String pass ="bahabaha40";
           String emailhost="smtp.gmail.com";
           Transport transport =newSession.getTransport("smtp");
           transport.connect(emailhost,fromuser,pass);
           transport.sendMessage( message, message.getAllRecipients());
           transport.close();
           
           
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Inscription valide check your Mail");
        alert.show();
    }

    @FXML
    private void annuller(ActionEvent event) {
        Stage s=(Stage)btn_annuler.getScene().getWindow();
        s.close();
    }
    
}
