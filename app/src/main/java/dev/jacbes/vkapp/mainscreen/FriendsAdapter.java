package dev.jacbes.vkapp.mainscreen;

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

import java.util.List;

import dev.jacbes.vkapp.R;
import dev.jacbes.vkapp.UserActivity;
import dev.jacbes.vkapp.model.VKUser;

/*
    Класс адаптера для заполнения каждого элемента recyclerview.
 */
public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder> {

    static Context context;
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
        LayoutInflater inflater = LayoutInflater.from(context);
        View friendItemView = inflater.inflate(layout, parent, false);

        return new FriendsViewHolder(friendItemView, context, vkUserList);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsViewHolder holder, int position) {
        holder.bind(vkUserList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return vkUserList.size();
    }

    /*
        Класс каждой строки, в котором она заполняется.
     */
    static class FriendsViewHolder extends RecyclerView.ViewHolder {

        Context context;
        List<VKUser> vkUserList;
        TextView position;
        TextView firstName;
        TextView lastName;
        ImageView avatar;
        TextView statusOnline;

        public FriendsViewHolder(@NonNull View itemView, Context context, List<VKUser> vkUserList) {
            super(itemView);

            this.context = context;
            this.vkUserList = vkUserList;
            this.position = itemView.findViewById(R.id.position);
            this.firstName = itemView.findViewById(R.id.first_name);
            this.lastName = itemView.findViewById(R.id.last_name);
            this.avatar = itemView.findViewById(R.id.avatar);
            this.statusOnline = itemView.findViewById(R.id.onlineStatus);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intentToGoToUserActivity();
                }
            });

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
        }

        void intentToGoToUserActivity (){
            VKUser user;
            Intent toInfo = null;

            toInfo = new Intent(context, UserActivity.class);
            toInfo.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            user = vkUserList.get(Integer.parseInt(position.getText().toString()) - 1);


            toInfo.putExtra("firstName", user.getFirstName());
            toInfo.putExtra("lastName", user.getLastName());
            toInfo.putExtra("photoOriginalURL", user.getPhotoOriginalURL());
            toInfo.putExtra("status", user.getStatus());
            toInfo.putExtra("domain", user.getDomain());
            toInfo.putExtra("mobilePhone", user.getMobilePhone());
            toInfo.putExtra("dataOfBirth", user.getDataOfBirth());

            context.startActivity(toInfo);
        }
    }
}
