package br.usjt.appanimaldonate.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.usjt.appanimaldonate.R;
import br.usjt.appanimaldonate.model.Animal;
import br.usjt.appanimaldonate.model.AnimalRepository;
import br.usjt.appanimaldonate.model.AnimalViewModel;
import br.usjt.appanimaldonate.util.ImageUtil;

public class AnunciosAdapter extends RecyclerView.Adapter<AnunciosAdapter.AnunciosHolder> {

    private List<Animal> results = new ArrayList<>();
    private static AnunciosAdapter.ItemClickListener itemClickListener;
    private ItemClickListener ClickListener;
    private AnimalRepository animalRepository;
    private Animal animal;

    @NonNull
    @Override
    public AnunciosAdapter.AnunciosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.anuncio_item, parent, false);

        return new AnunciosAdapter.AnunciosHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnunciosAdapter.AnunciosHolder holder, int position) {

        Animal animal = results.get(position);

        holder.textViewNomeAnimal.setText(animal.getNomeAnimal());
        holder.textViewEspecieAnimal.setText(animal.getEspecieAnimal());
        holder.textViewRacaAnimal.setText(animal.getRacaAnimal());
        holder.textViewPorteAnimal.setText(animal.getPorteAnimal());
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
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Animal> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    class AnunciosHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView textViewNomeAnimal;
        private TextView textViewEspecieAnimal;
        private TextView textViewRacaAnimal;
        private TextView textViewPorteAnimal;
        private TextView textViewIdadeAnimal;
        private TextView textViewGeneroAnimal;
        private TextView textViewVacinaAnimal;
        private TextView textViewCastracaoAnimal;
        private TextView textViewInformacaoAnimal;
        private Button editarAnuncioButton;
        private Button excluirAnuncioButton;
        private ImageView fotoCard;


        public AnunciosHolder(@NonNull View itemView) {
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
            editarAnuncioButton = itemView.findViewById(R.id.editarAnuncioButton);
            editarAnuncioButton.setOnClickListener(this);
            excluirAnuncioButton = itemView.findViewById(R.id.excluirAnuncioButton);
            excluirAnuncioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Animal animal = new Animal();

                    String id = results.get(getAdapterPosition()).getId();

                    animal.setId(id);

                    animalRepository.deletarAnimal(animal);
                }
            });

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(itemClickListener != null) {
                itemClickListener.onClick(getAdapterPosition(), results.get(getAdapterPosition()));
            }
        }
    }


    public void setOnItemClickListener(AnunciosAdapter.ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onClick(int position, Animal animal);
    }

}
