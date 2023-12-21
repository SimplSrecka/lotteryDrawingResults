package si.fri.rso.simplsrecka.lotterydrawingresults.lib;

import java.time.Instant;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LotteryDrawingResult {

    private Integer id;
    private Integer ticketId;
    private LocalDate drawingDate;
    private String winningCombination;
    private String lotteryCategory;
    private BigDecimal totalPrize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDate getDrawingDate() {
        return drawingDate;
    }

    public void setDrawingDate(LocalDate drawingDate) {
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
}
