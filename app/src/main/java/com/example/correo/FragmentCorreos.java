package com.example.correo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.correo.Modelo.Mail;

import java.util.ArrayList;

public class FragmentCorreos extends Fragment {

    private ArrayList<Mail> mails;
    private ICorreoListener listener;
    private RecyclerView rvCorreos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_correo,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvCorreos=getView().findViewById(R.id.rvCorreos);
        Parser parser= new Parser(getActivity());
        parser.parse();
        RvAdapterCorreo rvAdapterCorreo= new RvAdapterCorreo(this,parser.getMails(),parser.getContactos(),listener);
        rvCorreos.setAdapter(rvAdapterCorreo);
        rvCorreos.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));



    }
}
