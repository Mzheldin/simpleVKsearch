package com.example.androidvksample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidvksample.R;

import org.json.JSONArray;
import org.json.JSONException;

public class UsersAdapter extends RecyclerView.Adapter<UsersViewHolder> {

    private int itemCount = 0;
    private JSONArray array;
    private StringBuilder stringBuilder = new StringBuilder();
    private int count = 0;

    public void setArray(JSONArray array) {
        this.array = array;
        count = array.length();
        itemCount = 0;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.list_user_by_id;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutIdForListItem, viewGroup, false);
        UsersViewHolder usersViewHolder = new UsersViewHolder(view, context);
        usersViewHolder.setTvUserNumber(String.valueOf(itemCount + 1));
        usersViewHolder.setTvViewHolderUser(getUserInfo());
        setHolderArr(usersViewHolder);
        return usersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder usersViewHolder, int i) {
        usersViewHolder.setTvUserNumber(String.valueOf(i));
        usersViewHolder.setTvViewHolderUser(getUserInfo(i));
        setHolderArr(usersViewHolder, i);
    }

    @Override
    public int getItemCount() {
        return count;
    }

    private String getUserInfo(){
        stringBuilder.delete(0, stringBuilder.length());
        try {
            stringBuilder.append("Имя: ")
                    .append(array.getJSONObject(itemCount).getString("first_name"))
                    .append("\n").append("Фамилия: ")
                    .append(array.getJSONObject(itemCount).getString("last_name"));
            itemCount++;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private String getUserInfo(int i){
        stringBuilder.delete(0, stringBuilder.length());
        try {
            stringBuilder.append("Имя: ")
                    .append(array.getJSONObject(i).getString("first_name"))
                    .append("\n").append("Фамилия: ")
                    .append(array.getJSONObject(i).getString("last_name"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private void setHolderArr(UsersViewHolder usersViewHolder){
        try {
            usersViewHolder.setJUserData(array.getJSONObject(itemCount));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setHolderArr(UsersViewHolder usersViewHolder, int i){
        try {
            usersViewHolder.setJUserData(array.getJSONObject(i));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
