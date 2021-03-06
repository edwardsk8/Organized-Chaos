package layout.templates.Page;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Console;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import edu.group7.csc415.studentorganizer.R;

/**
 * Created by Lucas on 10/3/2017.
 *
 * This CardAdapter is a little hard to understand, but I'll try the best I can
 *
 * Basically this is where data held in our Card wrapper class meets XML
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    //Store a list of simple Card objects
    private List<Card> CardsList;

    //Constructor, pretty simple, takes a list of Cards to send to screen
    public CardAdapter(List<Card> CardsList) {
        this.CardsList = CardsList;
    }

    //Step 1
    //Where the rubber meets the road! Create a copy of the activity_card XML to become an individual Card
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card, parent, false);

        return new MyViewHolder(itemView);
    }

    //Step 2
    //Inner class, pulls views from XML and stores them for reference in Java objects
    //In a nut shell, this is the onCreate method for an individual CARD
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, dueDate;
        public ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.card_title);
            description = (TextView) view.findViewById(R.id.card_detail);
            dueDate = (TextView) view.findViewById(R.id.card_dueDate);
            icon = (ImageView) view.findViewById(R.id.card_image);


        }
    }

    //Step 3
    //Wire values from Card wrapper class to widgets
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Card c = CardsList.get(position);
        holder.title.setText(c.getTitle());
        holder.description.setText(c.getDescription());
        // TODO fix this to handle null \
        holder.icon.setImageResource(R.mipmap.ic_launcher_round);
        //TODO This is probably going to need some sort of date format

        try {
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy h:mm");
            holder.dueDate.setText(DATE_FORMAT.format(c.getDueDate()).toString());
        }catch (Exception e){
            holder.dueDate.setText("error");
        }

    }


    /*This is a method we're forced to implement,
        My guess is that we don't want anyone being able to touch our list of Cards, so,
        if they want to know how many we have they have to ask
     */
    @Override
    public int getItemCount() {
        return CardsList.size();
    }
}
