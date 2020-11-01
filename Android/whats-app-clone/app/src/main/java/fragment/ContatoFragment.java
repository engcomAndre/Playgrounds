package fragment;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.wapp_clone.andre.whatsappclone_smsmessenger.R;

import java.util.ArrayList;

import config.ConfiguracaoFirebase;
import helper.Preferencias;
import model.Contato;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContatoFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<String> contatos;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerContatos;



    public ContatoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerContatos);
    }

    @Override
    public void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerContatos);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contatos = new ArrayList<>();



        View view = inflater.inflate(R.layout.fragment_contato, container, false);
        listView = view.findViewById(R.id.lv_contatos);

        adapter = new ArrayAdapter(
                getActivity(),
                R.layout.lista_contatos,
                contatos
        );

        listView.setAdapter(adapter);

        Preferencias preferencias = new Preferencias(getActivity());
        String identificadorUsuariologado = preferencias.getIdentificador();

        firebase = ConfiguracaoFirebase.getFirebase()
                .child("Contatos")
                .child(identificadorUsuariologado);

        valueEventListenerContatos = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                contatos.clear();
                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    Contato contato = dados.getValue(Contato.class);
                    contatos.add(contato.getNome());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };



        return view;
    }

}
