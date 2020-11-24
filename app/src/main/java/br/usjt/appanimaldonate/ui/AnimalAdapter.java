package br.usjt.appanimaldonate.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.usjt.appanimaldonate.R;
import br.usjt.appanimaldonate.model.Animal;
import br.usjt.appanimaldonate.model.Usuario;
import br.usjt.appanimaldonate.util.ImageUtil;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalHolder> {

    private List<Animal> results = new ArrayList<>();
    private static ItemClickListener itemClickListener;

    public AnimalAdapter() {

    }

    @NonNull
    @Override
    public AnimalAdapter.AnimalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.animal_item, parent, false);

        return new AnimalHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AnimalAdapter.AnimalHolder holder, int position) {

        Animal animal = results.get(position);

        holder.textViewNomeAnimal.setText(animal.getNomeAnimal());
        holder.textViewEspecieAnimal.setText(animal.getEspecieAnimal());
        holder.textViewRacaAnimal.setText(animal.getRacaAnimal());
        holder.textViewPorteAnimal.setText(animal.getPorteAnimal());
        if (animal.getPorteAnimal().equals("Selecione o Porte")){
            holder.textViewPorteAnimal.setText("Não informado");
        }
        else{
            holder.textViewPorteAnimal.setText(animal.getPorteAnimal());
        }
        holder.textViewGeneroAnimal.setText(animal.getGeneroAnimal());
        holder.textViewIdadeAnimal.setText(animal.getIdadeAnimal());
        if(animal.isVacina()){
            holder.textViewVacinaAnimal.setText("Vacinado");
        }
        else{
            holder.textViewVacinaAnimal.setText("Não Vacinado");
            holder.textViewInformacaoAnimal.setText("Não possui vacinação");
        }
        if(animal.isCastrado()){
            holder.textViewCastracaoAnimal.setText("Animal Castrado");
        }
        else{
            holder.textViewCastracaoAnimal.setText("Animal não Castrado");
        }

        if(animal.getImagem()==null || animal.getImagem().isEmpty()){
            holder.fotoCard.setImageResource(R.drawable.ic_photo);
        }else{
            holder.fotoCard.setImageBitmap(ImageUtil.decode(animal.getImagem()));
        }

        holder.textViewInformacaoAnimal.setText(animal.getInformacao());
        if(animal.getInformacao().equals("")){
            holder.textViewInformacaoAnimal.setText("Vacinas não informadas");
        }
        else{
            holder.textViewInformacaoAnimal.setText(animal.getInformacao());
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Animal> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    class AnimalHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView textViewNomeAnimal;
        private TextView textViewEspecieAnimal;
        private TextView textViewRacaAnimal;
        private TextView textViewPorteAnimal;
        private TextView textViewIdadeAnimal;
        private TextView textViewGeneroAnimal;
        private TextView textViewVacinaAnimal;
        private TextView textViewCastracaoAnimal;
        private TextView textViewInformacaoAnimal;
        private Button  adocaoAnuncioButton;
        private ImageView fotoCard;


        public AnimalHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomeAnimal = itemView.findViewById(R.id.textViewNomeAnimal);
            textViewEspecieAnimal = itemView.findViewById(R.id.textViewEspecieAnimal);
            textViewRacaAnimal = itemView.findViewById(R.id.textViewRacaAnimal);
            textViewPorteAnimal = itemView.findViewById(R.id.textViewPorteAnimal);
            textViewIdadeAnimal = itemView.findViewById(R.id.textViewIdadeAnimal);
            textViewGeneroAnimal = itemView.findViewById(R.id.textViewGeneroAnimal);
            textViewVacinaAnimal = itemView.findViewById(R.id.textViewVacinaAnimal);
            textViewCastracaoAnimal = itemView.findViewById(R.id.textViewCastracaoAnimal);
            textViewInformacaoAnimal = itemView.findViewById(R.id.textViewInformacaoVacAnimal);
            fotoCard = itemView.findViewById(R.id.fotoCard);
            adocaoAnuncioButton = itemView.findViewById(R.id.adocaoAnuncioButton);
            adocaoAnuncioButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemClickListener != null) {
                itemClickListener.onItemClick(getAdapterPosition(), results.get(getAdapterPosition()));
            }
        }
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onItemClick(int position, Animal animal);
    }

}


