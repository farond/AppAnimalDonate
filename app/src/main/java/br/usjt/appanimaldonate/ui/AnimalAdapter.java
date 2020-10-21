package br.usjt.appanimaldonate.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.usjt.appanimaldonate.R;
import br.usjt.appanimaldonate.model.Animal;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalHolder> {

    private List<Animal> results = new ArrayList<>();
    private static ItemClickListener itemClickListener;

    @NonNull
    @Override
    public AnimalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.animal_item, parent, false);

        return new AnimalHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalAdapter.AnimalHolder holder, int position) {

        Animal animal = results.get(position);

        holder.textViewNomeAnimal.setText(animal.getNomeAnimal());
        holder.textViewEmail.setText(animal.getEmailAnimal());
        holder.textViewTelefone.setText(animal.getTelefone());


    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
