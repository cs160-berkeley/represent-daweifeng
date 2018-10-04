package io.dawei.represent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class RepresentativeListAdapter extends ArrayAdapter<Representative> {

    Context mCtx;
    int resource;
    List<Representative> representativeList;

    public RepresentativeListAdapter(Context mCtx, int resource, List<Representative> representativeList) {
        super(mCtx, resource, representativeList);

        this.mCtx = mCtx;
        this.resource = resource;
        this.representativeList = representativeList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(resource, null);

        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewParty = view.findViewById(R.id.textViewParty);
        TextView textViewPhone = view.findViewById(R.id.textViewPhone);
        TextView textViewContact = view.findViewById(R.id.textViewContact);
        TextView textViewWeb = view.findViewById(R.id.textViewWeb);
        ImageView imageView = view.findViewById(R.id.imageView);

        Button buttonView = view.findViewById(R.id.buttonView);

        Representative representative = representativeList.get(position);
        textViewName.setText(representative.getName());
        textViewParty.setText(representative.getParty());
        textViewPhone.setText(representative.getPhoneNumber());
        textViewContact.setText(representative.getContact_form());
        textViewWeb.setText(representative.getUrl());

//        Fetch image
        String imageUrl = "https://theunitedstates.io/images/congress/225x275/" + representative.getBioguide_id() + ".jpg";
        Picasso.get().load(imageUrl).resize(180, 180).centerCrop().transform(new CircleTransform(600,0)).into(imageView);
        return view;

    }
}
