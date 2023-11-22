package ma.ofppt.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ma.ofppt.recyclerview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements PersonAdaper.Listener {
    ActivityMainBinding binding;
    List<Person> personList = new ArrayList<>();
    PersonAdaper personAdaper;

    int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        personAdaper = new PersonAdaper(personList,this);

        binding.recycler.setAdapter(personAdaper);
        binding.btnAdd.setOnClickListener(view ->{
            String nom = binding.editNom.getText().toString();
            String prenom = binding.editPrenom.getText().toString();
            if(!nom.isEmpty() && !prenom.isEmpty() ){
                Person person = new Person(nom,prenom);
                if(index==-1){
                    personList.add(person);
                }else{
                    personList.set(index,person);
                }
            index = -1;
                binding.editPrenom.setText("");
                binding.editNom.setText("");
                personAdaper.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDelete(int position) {
        personList.remove(position);
        personAdaper.notifyDataSetChanged();
    }

    @Override
    public void onEdite(int position) {
        index = position;
        Person person = personList.get(position);
        binding.editPrenom.setText(person.getPrenom());
        binding.editNom.setText(person.getNom());
    }
}