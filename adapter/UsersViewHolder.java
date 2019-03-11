package com.example.androidvksample.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.androidvksample.ChildActivity;
import com.example.androidvksample.R;
import com.example.androidvksample.utils.URLFields;

import org.json.JSONException;
import org.json.JSONObject;

class UsersViewHolder extends RecyclerView.ViewHolder {

    private TextView tvUserNumber;
    private TextView tvViewHolderUser;
    private Class destinationActivity;
    private Intent childActivityIntent;
    private StringBuilder stringBuilder;

    private JSONObject JUserData;

    UsersViewHolder(@NonNull final View itemView, final Context context) {
        super(itemView);
        tvUserNumber = itemView.findViewById(R.id.tv_user_number);
        tvViewHolderUser = itemView.findViewById(R.id.tv_view_holder_user);
        stringBuilder = new StringBuilder();

        itemView.setOnClickListener(v -> {
            destinationActivity = ChildActivity.class;
            childActivityIntent = new Intent(context, destinationActivity);
            childActivityIntent.putExtra(Intent.EXTRA_TEXT, formExtraText());
            context.startActivity(childActivityIntent);

//                int positionIndex = getAdapterPosition();
//                Toast toast = Toast.makeText(context, "Element " + positionIndex + " was clicked!", Toast.LENGTH_SHORT);
//                toast.show();
        });
    }

    private String formExtraText(){
        stringBuilder.delete(0, stringBuilder.length());
        String n = "\n";
        try {
            stringBuilder
                    .append("id: ").append(String.valueOf(JUserData.getInt(URLFields.ID.getValue()))).append(n)
                    .append("Имя: ").append(JUserData.getString(URLFields.FIRST_NAME.getValue())).append(n)
                    .append("Фамилия: ").append(JUserData.getString(URLFields.LAST_NAME.getValue())).append(n)
                    .append("О себе: ").append(JUserData.getString(URLFields.ABOUT.getValue())).append(n)
                    .append("Дата рождения: ").append(JUserData.getString(URLFields.BDATE.getValue())).append(n)
                    .append("Интересы: ").append(JUserData.getString(URLFields.INTERESTS.getValue())).append(n)
                    .append("Родной город: ").append(JUserData.getString(URLFields.HOME_TOWN.getValue())).append(n)
                    .append("Онлайн: ").append(String.valueOf(JUserData.getInt(URLFields.ONLINE.getValue())));
        } catch (JSONException e){
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    void setJUserData(JSONObject jUserData){
        this.JUserData = jUserData;
    }

    void setTvUserNumber(String userNumberText){
        tvUserNumber.setText(userNumberText);
    }

    void setTvViewHolderUser(String viewHolderUserText){
        tvViewHolderUser.setText(viewHolderUserText);
    }
}
