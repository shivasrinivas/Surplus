package edu.njit.surplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.njit.surplus.R;
import edu.njit.surplus.models.Request;

/**
 * Created by srini on 11/12/17.
 */

public class RequestListAdapter extends RecyclerView.Adapter<RequestListAdapter.ViewHolder> {
    private List<Request> requests = new ArrayList();
    private RequestListAdapter.RequestItemListener listener;

    public RequestListAdapter(Context context, List<Request> requests) {
        listener = (RequestListAdapter.RequestItemListener) context;
        this.requests = requests;
    }

    @Override
    public RequestListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_people_item, parent, false);
        return new RequestListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RequestListAdapter.ViewHolder holder, final int position) {
        final Request post = requests.get(position);
        holder.tvItemName.setText(post.getNoOfPeopleWaiting() + " people waiting for food");
        holder.tvItemExpiry.setText("Expiry");
        if (post.getExpectingCost() == 0) {
            holder.tvItemPrice.setText("Expecting free of cost");
        } else {
            holder.tvItemPrice.setText("Can pay $" + post.getExpectingCost());
        }
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRequestItemClicked(post.getPostId());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (requests != null) {
            return requests.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItemName;
        TextView tvItemExpiry;
        TextView tvItemPrice;
        View rootView;

        ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            tvItemName = (TextView) itemView.findViewById(R.id.tv_item_name);
            tvItemExpiry = (TextView) itemView.findViewById(R.id.tv_item_expiry);
            tvItemPrice = (TextView) itemView.findViewById(R.id.tv_item_price);
        }
    }

    public interface RequestItemListener {
        void onRequestItemClicked(int postId);
    }
}
