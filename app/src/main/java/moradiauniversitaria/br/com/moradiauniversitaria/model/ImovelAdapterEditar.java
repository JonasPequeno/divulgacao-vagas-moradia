package moradiauniversitaria.br.com.moradiauniversitaria.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import moradiauniversitaria.br.com.moradiauniversitaria.R;

public class ImovelAdapterEditar extends RecyclerView.Adapter<ImovelAdapterEditar.ListCardViewHolder> {



    private List<Imovel> pratos;
    private ImovelAdapterEditar.CardOnClickListener cardOnClickListener;

    public ImovelAdapterEditar(List<Imovel> pratos, ImovelAdapterEditar.CardOnClickListener cardOnClickListener){
        this.pratos = pratos;
        this.cardOnClickListener = cardOnClickListener;
    }

    //Cria novas views
    @NonNull
    @Override
    public ImovelAdapterEditar.ListCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //criando nova view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vagalista, parent, false);

        ImovelAdapterEditar.ListCardViewHolder listCardViewHolder = new ImovelAdapterEditar.ListCardViewHolder(view);
        return listCardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ImovelAdapterEditar.ListCardViewHolder holder, final int position) {
        Imovel imovel = pratos.get(position);


        holder.sobreVaga.setText(imovel.getSobreVaga());
        holder.sobreImovel.setText(imovel.getSobreImovel());

        holder.imagem.getLayoutParams().width = 200;
        holder.imagem.getLayoutParams().height = 200;

        Picasso.get()
                .load(imovel.getFoto())
                .placeholder(R.mipmap.ic_launcher) // optional
                .resize(256, 256).centerCrop()
                .error(R.mipmap.ic_launcher) //if error
                .into(holder.imagem);


        //setar a imagem
        //holder.imagem.setImageDrawable(contexto.getResources().getDrawable);
        if(cardOnClickListener != null) {
            holder.btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Clicou aqui", "nao foi editar");
                    cardOnClickListener.onClickCard(holder.itemView, position);
                }
            });
            holder.btnRemover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("asdasdasdasd","doasjdpoajpsodpajppa");
                    cardOnClickListener.remover(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return pratos != null || !pratos.isEmpty() ? pratos.size() : 0;
    }

    public static class ListCardViewHolder extends RecyclerView.ViewHolder{
        ImageView imagem;
        TextView sobreImovel, sobreVaga, estado;
        Button btnEditar, btnRemover;

        public ListCardViewHolder(@NonNull View itemView) {
            super(itemView);

            imagem = itemView.findViewById(R.id.imageEditar);
            sobreImovel = itemView.findViewById(R.id.sobreImovel);
            sobreVaga = itemView.findViewById(R.id.sobreVaga);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnRemover = itemView.findViewById(R.id.btnRemover);

        }
    }

    public interface CardOnClickListener {
        void onClickCard(View view, int idx);
        void remover (View view, int idx);
    }
}

/*
    private Context contexto;
    private List<Imovel> listaDeImoveis;
    private ImovelAdapter.CardOnClickListener cardOnClickListener;

    public ImovelAdapter(Context contexto, List<Imovel> listaDeImoveis,
                         ImovelAdapter.CardOnClickListener cardOnClickListener) {
        this.contexto = contexto;
        this.listaDeImoveis = listaDeImoveis;
        this.cardOnClickListener = cardOnClickListener;
    }

    @NonNull
    @Override
    public _ImovelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(contexto);
        View view = inflater.inflate(R.layout.recycler_view_item,null);
        _ImovelViewHolder holder = new _ImovelViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull _ImovelViewHolder holder, int position) {
        Imovel imovel = listaDeImoveis.get(position);

        holder.sobreVaga.setText(imovel.getSobreVaga());
        holder.sobreImovel.setText(imovel.getSobreImovel());
        //setar a imagem
        //holder.imagem.setImageDrawable(contexto.getResources().getDrawable);
    }

    @Override
    public int getItemCount() {
        return listaDeImoveis.size();
    }

    class _ImovelViewHolder extends RecyclerView.ViewHolder {

        ImageView imagem;
        TextView sobreImovel, sobreVaga;

        public _ImovelViewHolder(@NonNull View itemView) {
            super(itemView);

            imagem = itemView.findViewById(R.id.imagem);
            sobreImovel = itemView.findViewById(R.id.sobreImovel);
            sobreVaga = itemView.findViewById(R.id.sobreVaga);
        }
    }

    public interface CardOnClickListener {
        void onClickCard(View view, int idx);
    }
*/

