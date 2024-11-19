package com.webecommerce.dto.discount;

import com.webecommerce.constant.DiscountConstant;
import com.webecommerce.dto.BaseDTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'tháng' M 'năm' yyyy, HH:mm", new Locale("vi", "VN"));
        return this.endDate.format(formatter);
    }

    public String getStringStartDate () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'tháng' M 'năm' yyyy, HH:mm", new Locale("vi", "VN"));
        return this.startDate.format(formatter);
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

    private String status;

    public String getStatus() {

        if (status != null) return status;

        if (endDate.isAfter(LocalDateTime.now()) && startDate.isAfter(LocalDateTime.now())) {
            status = DiscountConstant.UPCOMING;
        } else if (startDate.isBefore(LocalDateTime.now()) && endDate.isAfter(LocalDateTime.now())) {
            status = DiscountConstant.VALID;
        } else
            status = DiscountConstant.EXPIRED;

        return status;
    }

    public String getBootstrapClassStatus () {
        return DiscountConstant.getClassBootstrap(
                this.getStatus()
        );
    }
}
