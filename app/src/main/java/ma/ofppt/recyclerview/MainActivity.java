package ma.ofppt.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ma.ofppt.recyclerview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    List<Person> personList = new ArrayList<>();
    PersonAdaper personAdaper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        personAdaper = new PersonAdaper(personList);

        binding.recycler.setAdapter(personAdaper);
        binding.btnAdd.setOnClickListener(view ->{
            String nom = binding.editNom.getText().toString();
            String prenom = binding.editPrenom.getText().toString();
            if(!nom.isEmpty() && !prenom.isEmpty() ){
                personList.add(new Person(nom,prenom));
                personAdaper.notifyDataSetChanged();
            }
        });
    }
}