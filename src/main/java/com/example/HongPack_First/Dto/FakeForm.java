package com.example.HongPack_First.Dto;

public class FakeForm {
    private String title;

    private String content;

    public FakeForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "FakeForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
