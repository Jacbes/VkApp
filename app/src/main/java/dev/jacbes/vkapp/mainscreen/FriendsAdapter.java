package dev.jacbes.vkapp.mainscreen;

import static dev.jacbes.vkapp.MainActivity.friendsList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import dev.jacbes.vkapp.R;
import dev.jacbes.vkapp.userscreen.UserActivity;
import dev.jacbes.vkapp.model.VKUser;

/*
    Класс адаптера для заполнения каждого элемента recyclerview.
 */
public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder> {

    int layout;

    public FriendsAdapter(int layout) {
        this.layout = layout;
    }

    @NonNull
    @Override
    public FriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View friendItemView = inflater.inflate(layout, parent, false);

        return new FriendsViewHolder(friendItemView, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsViewHolder holder, int position) {
        holder.bind(friendsList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }

    /*
        Класс каждой строки, в котором она заполняется.
     */
    static class FriendsViewHolder extends RecyclerView.ViewHolder {

        Context context;

        View itemView;
        TextView position;
        TextView firstName;
        TextView lastName;
        ImageView avatar;
        TextView statusOnline;

        public FriendsViewHolder(@NonNull View itemView, Context context) {
            super(itemView);

            this.context = context;
            this.itemView = itemView;
            this.position = itemView.findViewById(R.id.position);
            this.firstName = itemView.findViewById(R.id.first_name);
            this.lastName = itemView.findViewById(R.id.last_name);
            this.avatar = itemView.findViewById(R.id.avatar);
            this.statusOnline = itemView.findViewById(R.id.onlineStatus);
        }

        void bind(VKUser friend, int position) {
            this.position.setText(String.valueOf(position + 1));
            this.firstName.setText(friend.getFirstName());
            this.lastName.setText(friend.getLastName());
            Glide.with(context)
                    .load(friend.getPhotoURL())
                    .into(avatar);

            if (friend.getOnlineStatus() == 1) {
                this.statusOnline.setText("Online");
            } else {
                this.statusOnline.setText("");
            }

            itemView.setOnClickListener(view -> intentToGoToUserActivity(friend, position));
        }

        void intentToGoToUserActivity(VKUser friend, int position) {
            Intent toInfo  = new Intent(context, UserActivity.class);
            toInfo.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            toInfo.putExtra("position", position);
            toInfo.putExtra("id", friend.getId());

            context.startActivity(toInfo);
        }
    }
}
