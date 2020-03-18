package com.training.coronavirus;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class Question implements Parcelable {
    int id;
    String text;
    ArrayList<String> choicesList = new ArrayList<String>();
    String choices;

    JsonArray jsonChoices;

    public Question(int id, String text, String choices) {
        this.id = id;
        this.text = text;
        this.choices = choices;
    }


    public Question() {


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    public ArrayList<String> getChoicesList() {
        return choicesList;
    }

    public ArrayList<String> setChoicesList(String Choices) {
        //  this.jsonChoices = new Gson().fromJson(Choices,JSONArray.class);
        Log.e("Jsonarrayconv", "" + choices);
        JsonParser parser = new JsonParser();
        JsonElement elem = parser.parse(Choices);

        this.jsonChoices = elem.getAsJsonArray();

        for (int i = 0; i < jsonChoices.size(); i++) {

            choicesList.add(String.valueOf(jsonChoices.get(i)));

        }
        return choicesList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +

                ", choicesList=" + choicesList +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(text);
        parcel.writeSerializable(choicesList);
        parcel.writeString(choices);

    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    private Question(Parcel in) {
        this.id = in.readInt();
        this.text = in.readString();
        this.choicesList = (ArrayList<String>) in.readSerializable();
        this.choices = in.readString();
    }
}
