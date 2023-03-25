package com.example.customlistwithnavigationwithrecicleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customlistwithnavigationwithrecicleview.model.Card;

import java.util.ArrayList;

public class CardListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {




    private OnCardClickListener onCardClickListener;

    public interface OnCardClickListener{
        void onCardClick(Card card);
    }

    private final ArrayList<Card> cardList;

    public CardListAdapter(ArrayList<Card> cardList){this.cardList=cardList;}


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        return new ItemCardViewHolder(itemView);
    }

    class ItemCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView cardValue1,cardValue2;

        public ImageView cardImage;

        ItemCardViewHolder(@NonNull View itemView){
            super(itemView);
            cardValue1 = itemView.findViewById(R.id.tv_cardValue1);
            cardValue2 = itemView.findViewById(R.id.tv_cardValue2);
            cardImage = itemView.findViewById(R.id.iv_cardImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                Card card = cardList.get(position);

                if(onCardClickListener !=null){
                    onCardClickListener.onCardClick(card);
                }
            }
        }
    }

    public void setOnCardClickListener(OnCardClickListener listener){
        this.onCardClickListener = listener;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Card card = cardList.get(position);
        if(holder instanceof ItemCardViewHolder){

            switch (card.getValue()){
                case 1:
                    ((ItemCardViewHolder) holder).cardValue1.setText("A");
                    ((ItemCardViewHolder) holder).cardValue2.setText("A");
                    break;
                case 11:
                    ((ItemCardViewHolder) holder).cardValue1.setText("J");
                    ((ItemCardViewHolder) holder).cardValue2.setText("J");
                    break;
                case 12:
                    ((ItemCardViewHolder) holder).cardValue1.setText("Q");
                    ((ItemCardViewHolder) holder).cardValue2.setText("Q");
                    break;
                case 13:
                    ((ItemCardViewHolder) holder).cardValue1.setText("K");
                    ((ItemCardViewHolder) holder).cardValue2.setText("K");
                    break;
                case 14:
                    ((ItemCardViewHolder) holder).cardValue1.setText("JOKER");
                    ((ItemCardViewHolder) holder).cardValue2.setText("JOKER");
                    break;
                default:
                    ((ItemCardViewHolder) holder).cardValue1.setText(String.valueOf(card.getValue()));
                    ((ItemCardViewHolder) holder).cardValue2.setText(String.valueOf(card.getValue()));
            }



            ((ItemCardViewHolder) holder).cardImage.setImageResource(card.isOpened() ? card.getImageResource() : R.drawable.baseline_question_mark_24);
            ((ItemCardViewHolder) holder).cardImage.setAlpha(card.isOpened() ? 1.0f : 0.5f);
            ((ItemCardViewHolder) holder).cardValue1.setAlpha(card.isOpened() ? 1.0f : 0.0f);
            ((ItemCardViewHolder) holder).cardValue2.setAlpha(card.isOpened() ? 1.0f : 0.0f);

        }
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
