package com.example.customlistwithnavigationwithrecicleview.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.TypedArrayUtils;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customlistwithnavigationwithrecicleview.CardListAdapter;
import com.example.customlistwithnavigationwithrecicleview.R;
import com.example.customlistwithnavigationwithrecicleview.model.Card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class LuckyChoiceFragment extends Fragment {

    View view;

    private RecyclerView recyclerView;
    private CardListAdapter adapter;
    private ArrayList<Card> cards = new ArrayList<>();
    private int moves=0;
    private boolean gameEnd = false;

    TextView movesText;
    Button newGame;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_choice,container,false);

        recyclerView = view.findViewById(R.id.rv_choice_game);
        movesText = view.findViewById(R.id.tv_choice_moves);
        newGame = view.findViewById(R.id.btn_choice_start);

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame.setVisibility(View.GONE);

                cards.clear();
                gameEnd=false;
                movesText.setText("");
                moves=0;
                createCardList();
                adapter = new CardListAdapter(cards);
                Toast.makeText(getContext(), "New game started", Toast.LENGTH_SHORT).show();
                recyclerView.setVisibility(View.VISIBLE);

                adapter.setOnCardClickListener(new CardListAdapter.OnCardClickListener() {
                    @Override
                    public void onCardClick(Card card) {

                        int pos = cards.indexOf(card);
                        if(gameEnd==true){
                            return;
                        }
                        if(card.isOpened()){
                            Toast.makeText(getContext(), "Card already opened", Toast.LENGTH_SHORT).show();
                        }else{
                            card.setOpened(true);
                            adapter.notifyItemChanged(pos);
                            if(moves<20){
                                moves++;
                                movesText.setText("MOVES: "+String.valueOf(moves)+"/20");
                                if(card.getValue()==14){
                                    newGame.setVisibility(View.VISIBLE);
                                    movesText.setText("YOU WIN!\nYOU FOUND THE JOKER IN "+String.valueOf(moves)+" MOVES\nPLAY AGAIN?");
                                    gameEnd = true;
                                }else if(moves==20){
                                    movesText.setText("YOU LOST, TRY AGAIN?");
                                    newGame.setVisibility(View.VISIBLE);
                                    gameEnd = true;
                                }
                            }else{
                                movesText.setText("YOU LOST, TRY AGAIN?");
                                newGame.setVisibility(View.VISIBLE);
                                gameEnd = true;
                            }
                            if(gameEnd){
                                for(Card singleCard : cards){
                                    if(singleCard.getValue()==14){
                                        singleCard.setOpened(true);
                                        singleCard.setImageResource(R.drawable.baseline_joker_invisible);
                                    }
                                }
                            }

                        }
                    }
                });

                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(),3,GridLayoutManager.VERTICAL,false));

            }
        });


        return view;
    }

    private void createCardList(){
        for(int i=1;i<=13;i++){
            cards.add(new Card(i,R.drawable.baseline_club));
            cards.add(new Card(i,R.drawable.baseline_diamond));
            cards.add(new Card(i,R.drawable.baseline_heart));
            cards.add(new Card(i,R.drawable.baseline_spade));
        }
        cards.add(new Card(14,R.drawable.baseline_joker));
        Collections.shuffle(cards);
    }

}
