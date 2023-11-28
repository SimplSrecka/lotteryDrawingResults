package si.fri.rso.simplsrecka.lotterydrawingresults.models.converters;

import si.fri.rso.simplsrecka.lotterydrawingresults.models.entities.LotteryDrawingResultsEntity;
import si.fri.rso.simplsrecka.lotterydrawingresults.lib.LotteryDrawingResult;

public class LotteryDrawingResultsConverter {

    public static LotteryDrawingResult toDto(LotteryDrawingResultsEntity entity) {
        LotteryDrawingResult dto = new LotteryDrawingResult();
        dto.setId(entity.getId());
        dto.setDrawingDate(entity.getDrawingDate());
        dto.setWinningCombination(entity.getWinningCombination());
        dto.setLotteryCategory(entity.getLotteryCategory());
        dto.setTotalPrize(entity.getTotalPrize());

        return dto;
    }

    public static LotteryDrawingResultsEntity toEntity(LotteryDrawingResult dto) {
        LotteryDrawingResultsEntity entity = new LotteryDrawingResultsEntity();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entity.setDrawingDate(dto.getDrawingDate());
        entity.setWinningCombination(dto.getWinningCombination());
        entity.setLotteryCategory(dto.getLotteryCategory());
        entity.setTotalPrize(dto.getTotalPrize());

        return entity;
    }
}
