package si.fri.rso.simplsrecka.lotterydrawingresults.lib;

import java.time.Instant;
import java.math.BigDecimal;

public class LotteryDrawingResult {

    private Integer id;
    private Instant drawingDate;
    private String winningCombination;
    private String lotteryCategory;
    private BigDecimal totalPrize;
    private Integer ticketId;
    private String name;
    private String description;
    private Double price;
    private Instant created;
    private Instant drawDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getDrawingDate() {
        return drawingDate;
    }

    public void setDrawingDate(Instant drawingDate) {
        this.drawingDate = drawingDate;
    }

    public String getWinningCombination() {
        return winningCombination;
    }

    public void setWinningCombination(String winningCombination) {
        this.winningCombination = winningCombination;
    }

    public String getLotteryCategory() {
        return lotteryCategory;
    }

    public void setLotteryCategory(String lotteryCategory) {
        this.lotteryCategory = lotteryCategory;
    }

    public BigDecimal getTotalPrize() {
        return totalPrize;
    }

    public void setTotalPrize(BigDecimal totalPrize) {
        this.totalPrize = totalPrize;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(Instant drawDate) {
        this.drawDate = drawDate;
    }
}
