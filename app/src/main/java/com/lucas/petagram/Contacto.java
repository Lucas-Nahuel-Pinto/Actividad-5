package com.lucas.petagram;

import android.media.MediaCas;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Contacto extends AppCompatActivity {

    private EditText nombre;
    private EditText correo;
    private EditText mensaje;
    private String   mensajeFull;
    private Button btnEnviar;
    private Session session;
    private String mailPet;
    private String passPet;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacto_layout);

        android.support.v7.widget.Toolbar miActionBar = findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        nombre = (EditText)findViewById(R.id.etNombre);
        correo = (EditText)findViewById(R.id.etCorreo);
        mensaje = (EditText)findViewById(R.id.etMensaje);
        btnEnviar = (Button)findViewById(R.id.btnEnviar);

        mailPet = "petagramsocial@gmail.com";
        passPet = "coursera2018";

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                Properties properties = new Properties();
                properties.put("mail.smtp.host","smtp.googlemail.com");
                properties.put("mail.smtp.socketFactory.port","465");
                properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.port","465");

                try{
                    session=Session.getDefaultInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(mailPet,passPet);
                        }
                    });

                    if( session != null)
                    {
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(mailPet));
                        message.setSubject("Contacto Petagram");
                        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mailPet));
                        mensajeFull = nombre.getText().toString() + " ("+correo.getText().toString()+")"+ " te ha enviado un mensaje:"+ System.lineSeparator() + mensaje.getText().toString();
                        message.setContent(mensajeFull,"text/html; charset=utf-8");
                        Transport.send(message);
                        Toast.makeText(getApplicationContext(), "El mensaje se ha enviado", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e)
                {
                    e.printStackTrace();

                }


        }

        });
    }
}
