package com.pedrofonseca.example;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Pedro Fonseca on 10/02/2016.
 */
public class CarFragment extends Fragment implements RecyclerViewOnClickListenerHack {

    //VARIAVEIS
    private RecyclerView mRecyclerView;
    private List<Car> mList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //abrir o fragment
        View view = inflater.inflate(R.layout.fragment_car, container, false);

        //Ir buscar o RecyclerView do fragment_car
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);

        //defenir que o tamanho é fixo
        mRecyclerView.setHasFixedSize(true);

        //um listener para se puder carregar mais items, no final da lista
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            //metodo obrigatorio, não utilizado, neste caso
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            //metodo obrigatorio
            //o que acontece quando se faz scroll
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //aqui o codigo que faz carregar mais items á medida que se faz scroll
                //igual ao carregado inicial
                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                CarAdapter adapter = (CarAdapter) mRecyclerView.getAdapter();

                //mas aqui faz-se uma verificação
                //quando o fim da lista e igual ao ultimo item visivel no layout
                //este vai buscar mais 10 items
                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    //este vai buscar mais 10 items
                    List<Car> listAux = ((MainActivity) getActivity()).getSetCarList(10);
                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }
            }
        });


        //Obrigatorio um LayoutManager
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());

        //defenir a sua orientação
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //defenir qual o RecyclerView que fica com as defenições do LinearLayoutManager
        mRecyclerView.setLayoutManager(llm);

        //vai buscar a lista a class MainActivity
        //que poderia ser directamente de uma Base de dados ou API de um webService
        mList = ((MainActivity) getActivity()).getSetCarList(10);

        //setar o adapter
        CarAdapter adapter = new CarAdapter(getActivity(), mList);

        //este adapter tera uma interface para o seu click
        adapter.setRecyclerViewOnClickListenerHack(this);

        //defenir o nosso adapter no nosso recycleView
        mRecyclerView.setAdapter( adapter );

        return view;
    }

    //metodo da inetrface, que dá funcionalidade ao click do item
    @Override
    public void onClickListener(View view, int position) {
        //mensagem da posição
        Toast.makeText(getActivity(), "Position: "+position, Toast.LENGTH_SHORT).show();

        //metodo que esta no CarAdapter, para remover items
        CarAdapter adapter = (CarAdapter) mRecyclerView.getAdapter();
        adapter.removeListItem(position);

    }


}
