package com.example.correo;

import android.content.Context;

import com.example.correo.Modelo.Account;
import com.example.correo.Modelo.Contacto;
import com.example.correo.Modelo.Mail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Parser {



    private Account myAccount;
    private ArrayList<Contacto> contactos;
    private ArrayList<Mail> mails;

    private Context context;

    public Parser(Context c){
        this.context=c;
        contactos=new ArrayList<>();
        mails= new ArrayList<>();
    }

    public void parse(){


        String json=null;

        try {
            InputStream inputStream= context.getResources().openRawResource(R.raw.correos);

            int size= inputStream.available();
            byte[] buffer= new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json=new String(buffer,"UTF-8");
            JSONTokener tokener= new JSONTokener(json);

            JSONArray array= new JSONArray(tokener);
            JSONObject object= array.getJSONObject(0);
            JSONObject account= object.getJSONObject("myAccount");
            int id= account.getInt("id");
            String name= account.getString("name");
            String firstSurname= account.getString("firstSurname");
            String email= account.getString("email");

            myAccount=new Account(id,name,firstSurname,email);

            JSONArray arrayContactos= object.getJSONArray("contacts");

            for(int i=0;i<arrayContactos.length();i++){
                JSONObject contact=arrayContactos.getJSONObject(i);

                id=contact.getInt("id");
                name=contact.getString("name");
                firstSurname=contact.getString("firstSurname");
                String secondSurname=contact.getString("secondSurname");
                String birth= contact.getString("birth");
                int foto= contact.getInt("foto");
                email= contact.getString("email");
                String phone1=contact.getString("phone1");
                String phone2=contact.getString("phone2");
                String address=contact.getString("address");
                contactos.add(new Contacto(id,name,firstSurname,secondSurname,birth,foto,email,phone1,phone2,address));
            }

            JSONArray arrayMails= object.getJSONArray("mails");

            for (int i =0; i<arrayMails.length();i++){
                JSONObject mail= arrayMails.getJSONObject(i);
                String from= mail.getString("from");
                String to= mail.getString("to");
                String subject =mail.getString("subject");
                String body= mail.getString("body");
                String sentOn= mail.getString("sentOn");
                boolean readed= mail.getBoolean("readed");
                boolean deleted= mail.getBoolean("deleted");
                boolean spam= mail.getBoolean("spam");

                mails.add(new Mail(from,to,subject,body,sentOn,readed,deleted,spam));
            }

        }catch (JSONException j){

        }catch (IOException ioe){

        }

    }

    public Account getMyAccount() {
        return myAccount;
    }

    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    public ArrayList<Mail> getMails() {
        return mails;
    }
}
