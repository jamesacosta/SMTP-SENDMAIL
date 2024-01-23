import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class Main {
    public static void main(String[] args) {

        //creamos el objeto properties
        Properties props = new Properties();

        //definimos el host de el servidor SMTP en este caso es smtp.gmail.com
        props.put("mail.smtp.host", "smtp.gmail.com");
        //nos conectamos al servidor especificando el puerto
        props.put("mail.smtp.port", "587");
        //indicamos que se requiere la autenticacion
        props.put("mail.smtp.auth", "true");
        //esta es la forma de asegurar la conexion SMTP
        props.put("mail.smtp.starttls.enable", "true");

        //guardamos la session haciendo uso de las props y new Authenticator
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            //damos las credenciales para autenticar
            protected PasswordAuthentication getPasswordAuthentication() {
                //credenciales para autenticacion para el servidor SMTP
                return new PasswordAuthentication("YOU-EMAIL@gmail.com", "YOU-PASSWORD");
            }
        });

        try {
            //se configura el nuevo mensaje pasando la sesion creada
            MimeMessage message = new MimeMessage(session);
            //se crea un Array de tipo InternetAddres
            InternetAddress[] addresses = {
                    //se crean diferenres instancias de InternetAddres con el true que es para verificar el
                    //formato de la dirrecion de correo proporcionada
                    new InternetAddress("destination1@example.com", true),
                    new InternetAddress("destination2@example.com",true),
                    new InternetAddress("destination3@example.com",true)
            };

            //agregamos el Array de correos a los cuales va dirigido el mensaje, el "to" puede ser tambien "cc","bcc".
            message.addRecipients(Message.RecipientType.TO, addresses);
            //titulo que llevara el correo
            message.setSubject("Tittle-mail");
            //mensaje de el correo
            message.setText("SEND-MESSAGE");
            //mensaje de enviando
            System.out.println("Sending...");
            //se transporta el mensaje
            Transport.send(message);
            //correo enviado con exito
            System.out.println("Sent message successfully...");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}