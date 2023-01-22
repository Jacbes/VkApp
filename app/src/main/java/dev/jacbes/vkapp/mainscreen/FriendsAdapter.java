package dev.jacbes.vkapp.mainscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.jacbes.vkapp.R;
import dev.jacbes.vkapp.model.VKUser;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder> {

    Context context;
    int layout;
    List<VKUser> vkUserList;

    public FriendsAdapter(Context context, int layout, List<VKUser> vkUserList) {
        this.context = context;
        this.layout = layout;
        this.vkUserList = vkUserList;
    }

    @NonNull
    @Override
    public FriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View friendItemView = inflater.inflate(layout, parent, false);

        return new FriendsViewHolder(friendItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsViewHolder holder, int position) {
        holder.bind(vkUserList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return vkUserList.size();
    }

    static class FriendsViewHolder extends RecyclerView.ViewHolder {

        TextView position;
        TextView firstName;
        TextView lastName;

        public FriendsViewHolder(@NonNull View itemView) {
            super(itemView);

            this.position = itemView.findViewById(R.id.position);
            this.firstName = itemView.findViewById(R.id.first_name);
            this.lastName = itemView.findViewById(R.id.last_name);
        }

        void bind(VKUser friend, int position) {
            this.position.setText(String.valueOf(position + 1));
            this.firstName.setText(friend.getFirstName());
            this.lastName.setText(friend.getLastName());
        }
    }
}