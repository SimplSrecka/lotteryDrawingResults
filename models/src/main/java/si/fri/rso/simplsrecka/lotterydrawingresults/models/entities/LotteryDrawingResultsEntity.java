package si.fri.rso.simplsrecka.lotterydrawingresults.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "lottery_drawing_results")
@NamedQueries({
        @NamedQuery(name = "LotteryDrawingResultsEntity.getAll",
                query = "SELECT l FROM LotteryDrawingResultsEntity l")
})
public class LotteryDrawingResultsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "drawing_date")
    private Instant drawingDate;

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

}
