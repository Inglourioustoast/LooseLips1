package com.example.supersecretproject;

public class ExampleItem {
    private int mImageResource;
    private String mText1;
    private String mText2;

    public ExampleItem(int imageResource, String tex1, String text2) {
        mImageResource = imageResource;
        mText1 = tex1;
        mText2 = text2;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }

}
