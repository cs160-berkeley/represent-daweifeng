package io.dawei.represent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CommitteeListAdapter extends ArrayAdapter<Committee> {

    Context context;
    int resource;
    List<Committee> committeeList;

    public CommitteeListAdapter(@NonNull Context context, int resource, @NonNull List<Committee> committeeList) {
        super(context, resource, committeeList);
        this.committeeList = committeeList;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null);

        TextView textViewCommitteeName = view.findViewById(R.id.textViewCommitteeName);
        TextView textViewCommittTitle = view.findViewById(R.id.textViewCommittTitle);
        TextView textViewCommitteEndDate = view.findViewById(R.id.textViewCommitteEndDate);

        Committee committee = committeeList.get(position);

        textViewCommitteeName.setText(committee.getName());
        textViewCommittTitle.setText(committee.getTitle());
        textViewCommitteEndDate.setText(committee.getEndDate());

        return view;
    }
}
