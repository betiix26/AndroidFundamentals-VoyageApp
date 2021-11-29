package com.travel.voyage.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * @author Beti
 */
@Entity
public class Trip implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "user_id", index = true)
    private long userId;
    private String name;
    private String destination;
    private int type;
    /**
     * Here we have: 0 - City Break, 1 - Sea Side or 2 - Mountains
     */
    private double price;

    @ColumnInfo(name = "start_date")
    private String startDate;

    @ColumnInfo(name = "end_date")
    private String endDate;
    private double rating;

    @ColumnInfo(name = "is_favorite")
    private boolean isFavorite;

    public Trip(long userId, String name, String destination, int type, double price, String startDate, String endDate, double rating) {
        this.userId = userId;
        this.name = name;
        this.destination = destination;
        this.type = type;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public int getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getRating() {
        return rating;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @NotNull
    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", user_id=" + userId +
                ", name='" + name + '\'' +
                ", destination='" + destination + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", rating=" + rating +
                '}';
    }
}
