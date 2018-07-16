package com.pedrofonseca.example;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pedro Fonseca on 10/02/2016.
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {

    //a utilização deste processo(RecyclerView) é muito pesado para o android
    //por essa razão se utiliza o ViewHolder para agilizar o processo

    //VARIAVEIS
    private List<Car> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    //CONSTRUTOR
    public CarAdapter(Context c, List<Car> l){
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //METODOS
    //metodo obrigatorio
    //dados apenas quando a view é carregada
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Log.i("LOG", "onCreateViewHolder()");

        //quando inicia é preciso fazer inflate ao layout
        View v = mLayoutInflater.inflate(R.layout.item_car, viewGroup, false);

        //chama a class criada mais abaixo e passa como parametro o layout criado atras,
        //que é uma view
        MyViewHolder mvh = new MyViewHolder(v);

        //retorna o processo
        return mvh;
    }

    //metodo obrigatorio
    //mais utilizado, onde todos os dados são chamados
    //e re_chamados
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {

        //para teste
        Log.i("LOG", "onBindViewHolder()");

        //coloca os dados de cada item na sua posição certa, criando uma listView
        //getPhoto,getModel,getBrand metodos criados manualmente na class Car
        myViewHolder.ivCar.setImageResource( mList.get(position).getPhoto() );
        myViewHolder.tvModel.setText(mList.get(position).getModel() );
        myViewHolder.tvBrand.setText( mList.get(position).getBrand() );
    }

    //metodo obrigatorio
    //tamanho da lista, antes de se autocarregar
    @Override
    public int getItemCount() {
        //neste caso 10
        return mList.size();
    }

    //metodo para o clic do item
    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        mRecyclerViewOnClickListenerHack = r;
    }

    //metodo para inserir items (carros)
    public void addListItem(Car c, int position){
        mList.add(c);
        notifyItemInserted(position);
    }

    //metodo para remover items
    public void removeListItem(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //VARIAVEIS
        public ImageView ivCar;
        public TextView tvModel;
        public TextView tvBrand;

        //CONSTRUTOR
        public MyViewHolder(View itemView) {
            super(itemView);

            //Vai buscar os dados do layout, referenciado no construtor como itemView
            ivCar = (ImageView) itemView.findViewById(R.id.iv_car);
            tvModel = (TextView) itemView.findViewById(R.id.tv_model);
            tvBrand = (TextView) itemView.findViewById(R.id.tv_brand);

            //torna os items clicaveis
            itemView.setOnClickListener(this);
        }

        //METODOS
        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListenerHack != null){
                mRecyclerViewOnClickListenerHack.onClickListener(v, getLayoutPosition());
            }
        }
    }
}
