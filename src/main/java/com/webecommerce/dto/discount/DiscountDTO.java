package com.webecommerce.dto.discount;

import com.webecommerce.dto.BaseDTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class DiscountDTO extends BaseDTO<DiscountDTO> {

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int discountPercentage;

    private boolean isOutStanding ;
    public boolean getIsOutStanding() {
        return isOutStanding;
    }
    public void setOutStanding(boolean outStanding) {
        isOutStanding = outStanding;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStringEndDate () {
        return this.endDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy, HH:mm"));
    }

    public String getStringStartDate () {
        return this.startDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy, HH:mm"));
    }

    public long getRemainingDates () {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, endDate);
        return duration.toDays();
    }

    public long getRemainingHours () {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, endDate);
        return duration.toHours() % 24;
    }
    public long getRemainingMinutes () {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, endDate);
        return duration.toMinutes() % 60;
    }
    public long getRemainingSeconds () {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, endDate);
        return duration.getSeconds() % (60 * 60) ;
    }

    // Hàm trả về chênh lệch phút
    public static long getMinutesDifference(LocalDateTime pastDateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(pastDateTime, now);
        return duration.toMinutes();
    }

    // Hàm trả về chênh lệch giây
    public static long getSecondsDifference(LocalDateTime pastDateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(pastDateTime, now);
        return duration.getSeconds();
    }


    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

}
