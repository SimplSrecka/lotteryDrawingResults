package si.fri.rso.simplsrecka.lotterydrawingresults.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "lottery_drawing_results")
@NamedQueries({
        @NamedQuery(name = "LotteryDrawingResultsEntity.getAll",
                query = "SELECT l FROM LotteryDrawingResultsEntity l"),
        @NamedQuery(name = "LotteryDrawingResultsEntity.getTicketByIdAndDate",
                query = "SELECT l FROM LotteryDrawingResultsEntity l WHERE l.ticketId = :ticketId and l.drawingDate = :drawingDate")
})
public class LotteryDrawingResultsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ticket_id")
    private Integer ticketId;

    @Column(name = "drawing_date")
    private LocalDate drawingDate;

    @Column(name = "winning_combination")
    private String winningCombination;

    @Column(name = "lottery_category")
    private String lotteryCategory;

    @Column(name = "total_prize")
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
