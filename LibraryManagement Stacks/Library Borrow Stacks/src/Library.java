public class Library {
    
    String title; 
    String author;

    public Library (String title, String author){
        this.title = title;
        this.author = author;

    }

    public String toString (){
        return title + " by " + author;
    }

}
