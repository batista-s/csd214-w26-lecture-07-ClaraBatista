package csd214.bookstore.pojos;

import csd214.bookstore.entities.DigitalMusicEntity;

import java.util.Objects;
import java.util.Scanner;

/**
 * DTO for {@link DigitalMusicEntity}
 */
public class DigitalMusic extends MusicCollection{
    private String link;

    public DigitalMusic() {
    }

    public DigitalMusic(String title, String artist, String genre, int year, double price, String link) {
        super(title, artist, genre, year, price, "DIGITAL MUSIC");
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public void edit(Scanner input) {
        super.edit(input);
        System.out.println("Edit Link [" + this.link + ":");
        setLink(getInput(input, this.link));
    }

    @Override
    public void initialize(Scanner input) {
        super.initialize(input);
        System.out.println("Enter Link:");
        setLink(getInput(input, "No link attached"));
        super.setName("DIGITAL MUSIC");
    }

    @Override
    public String toString() {
        return "Digital Music{" +
                "link='" + link + ", " + super.toString() +  '\'' +
                '}';
    }

    @Override
    public void sellItem(){
        System.out.println("Selling Digital Music (link: " + link + super.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DigitalMusic that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), link);
    }
}