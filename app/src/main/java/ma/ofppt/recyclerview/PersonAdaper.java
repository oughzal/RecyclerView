package ma.ofppt.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PersonAdaper extends RecyclerView.Adapter<PersonAdaper.ViewHoler> {

    interface  Listener{
        public void onDelete(int position);
        public  void onEdite(int position);
    }
    Listener listener;
    public PersonAdaper(List<Person> itemList,Listener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    List<Person> itemList = new ArrayList<>();
    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        Person person= itemList.get(position);
        holder.txtPrenom.setText(person.getPrenom());
        holder.txtNom.setText(person.getNom());
        holder.btnDelete.setOnClickListener(view ->{
            //itemList.remove(position);
            //notifyDataSetChanged();
            listener.onDelete(position);
        });
        holder.itemView.setOnClickListener(view ->{
            listener.onEdite(position);
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHoler extends  RecyclerView.ViewHolder{
        TextView txtNom,txtPrenom;
        ImageView btnDelete;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            txtNom = itemView.findViewById(R.id.txtNom);
            txtPrenom = itemView.findViewById(R.id.txtPrenom);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
