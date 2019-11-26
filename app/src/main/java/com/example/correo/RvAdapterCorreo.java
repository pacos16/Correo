package com.example.correo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.correo.Modelo.Contacto;
import com.example.correo.Modelo.Mail;

import java.util.ArrayList;

public class RvAdapterCorreo extends RecyclerView.Adapter<RvAdapterCorreo.CorreoViewHolder> {

    private Context context;
    private ArrayList<Mail> mails;
    private ArrayList<Contacto> contactos;
    ICorreoListener listener;
    public RvAdapterCorreo(Fragment fragment, ArrayList<Mail> mails,ArrayList<Contacto> contactos, ICorreoListener listener){
        this.context=fragment.getContext();
        this.mails=mails;
        this.listener=listener;
        this.contactos=contactos;
    }

    @NonNull
    @Override
    public CorreoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_correo,parent,false);
        return new CorreoViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CorreoViewHolder holder, int position ) {
        holder.bind(mails.get(position));
    }

    @Override
    public int getItemCount() {
        return mails.size();
    }

    public class CorreoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ICorreoListener listener;
        TextView tvNombre;
        TextView tvAsunto;
        TextView tvDescripcion;
        TextView tvFecha;
        TextView tvHora;


        public CorreoViewHolder(@NonNull View itemView, ICorreoListener listener) {
            super(itemView);
            this.listener=listener;
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvAsunto= itemView.findViewById(R.id.tvAsunto);
            tvDescripcion= itemView.findViewById(R.id.tvDescripcion);
            tvFecha= itemView.findViewById(R.id.tvFecha);
            tvHora= itemView.findViewById(R.id.tvHora);
        }

        public void bind(Mail m){
            String nombre="";
            for (Contacto c: contactos
                 ) {
                if(c.getEmail().equals(m.getFrom())){
                    nombre=c.getName().concat(" ").concat(c.getFirstSurname().concat(" ").concat(c.getSecondSurname()));
                }
            }
            tvNombre.setText(nombre);
            tvAsunto.setText(m.getSubject());
            tvDescripcion.setText(m.getBody());

        }

        @Override
        public void onClick(View v) {

        }
    }
}
