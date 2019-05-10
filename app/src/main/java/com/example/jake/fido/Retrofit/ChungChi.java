package com.example.jake.fido.Retrofit;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class ChungChi {

    String title;
    String description;
    String Uri;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return Uri;
    }

    public ChungChi(String uri, String title, String description) {
        this.title = title;
        this.description = description;
        Uri = uri;
    }

    public void setUri(String uri) {
        Uri = uri;
    }

    public ChungChi(String uri, String name, String description, String id) {
        this.title = title;
        this.description = description;
        Uri = uri;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
