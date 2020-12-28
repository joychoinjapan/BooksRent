package com.example.booksrent.model;

public class Book {
    private String book_name;
    private int required_age;
    private int pic;
    private boolean history;
    private boolean suspense;
    private boolean art;
    private String type_des;


    public Book(String book_name, int required_age,int pic, boolean history, boolean suspense, boolean art) {
        this.book_name = book_name;
        this.required_age = required_age;
        this.history = history;
        this.suspense = suspense;
        this.art = art;
        this.pic = pic;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getRequired_age() {
        return required_age;
    }

    public void setRequired_age(int required_age) {
        this.required_age = required_age;
    }

    public boolean isHistory() {
        return history;
    }

    public void setHistory(boolean history) {
        this.history = history;
    }

    public boolean isSuspense() {
        return suspense;
    }

    public void setSuspense(boolean suspense) {
        this.suspense = suspense;
    }

    public boolean isArt() {
        return art;
    }

    public void setArt(boolean art) {
        this.art = art;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getType(){
        type_des = "";
        if(this.history){
            type_des+="历史";
        }

        if(this.suspense){
            type_des+=",悬疑";
        }

        if(this.art){
            type_des+=",艺术";
        }

        return type_des;

    }

    @Override
    public String toString() {
        return "Book{" +
                "book_name='" + book_name + '\'' +
                ", required_age=" + required_age +
                ", pic=" + pic +
                ", history=" + history +
                ", suspense=" + suspense +
                ", art=" + art +
                '}';
    }
}
