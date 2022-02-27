package com.example.qrreceiptscanner.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qrreceiptscanner.R;

public class HomeRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		final View vw = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.row_home, parent, false);
		return new RecyclerView.ViewHolder(vw) {
		};
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
		final TextView tvTitle = holder.itemView.findViewById(R.id.row_home_title);
		final TextView tvSubtitle = holder.itemView.findViewById(R.id.row_home_subtitle);

		tvTitle.setText("Item " + (position + 1));
		tvSubtitle.setText("Subtitle " + position + 1);
	}

	@Override
	public int getItemCount() {
		return 20;
	}
}
