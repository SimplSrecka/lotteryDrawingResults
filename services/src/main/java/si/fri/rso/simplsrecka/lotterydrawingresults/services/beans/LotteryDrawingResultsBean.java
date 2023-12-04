package si.fri.rso.simplsrecka.lotterydrawingresults.services.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Timed;

import si.fri.rso.simplsrecka.lotterydrawingresults.lib.LotteryDrawingResult;
import si.fri.rso.simplsrecka.lotterydrawingresults.models.converters.LotteryDrawingResultsConverter;
import si.fri.rso.simplsrecka.lotterydrawingresults.models.entities.LotteryDrawingResultsEntity;



@RequestScoped
public class LotteryDrawingResultsBean {

    private Logger log = Logger.getLogger(LotteryDrawingResultsBean.class.getName());

    @Inject
    private EntityManager em;

    public List<LotteryDrawingResult> getLotteryDrawingResults() {
        TypedQuery<LotteryDrawingResultsEntity> query = em.createNamedQuery("LotteryDrawingResultsEntity.getAll", LotteryDrawingResultsEntity.class);
        List<LotteryDrawingResultsEntity> resultList = query.getResultList();
        return resultList.stream().map(LotteryDrawingResultsConverter::toDto).collect(Collectors.toList());
    }

    @Timed(name = "get_lottery_tickets_filter")
    public List<LotteryDrawingResult> getLotteryDrawingResultsFilter(UriInfo uriInfo) {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0).build();
        return JPAUtils.queryEntities(em, LotteryDrawingResultsEntity.class, queryParameters).stream()
                .map(LotteryDrawingResultsConverter::toDto).collect(Collectors.toList());
    }

    public LotteryDrawingResult getLotteryDrawingResult(Integer id) {
        LotteryDrawingResultsEntity entity = em.find(LotteryDrawingResultsEntity.class, id);
        if (entity == null) {
            throw new NotFoundException("Drawing Result not found");
        }
        return LotteryDrawingResultsConverter.toDto(entity);
    }

    @Gauge(name = "create_lottery_drawing_result", unit = "MetricUnits.NONE")
    public LotteryDrawingResult createLotteryDrawingResult(LotteryDrawingResult dto) {
        LotteryDrawingResultsEntity entity = LotteryDrawingResultsConverter.toEntity(dto);
        try {
            beginTx();
            em.persist(entity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
            throw new RuntimeException("Error creating drawing result", e);
        }
        return LotteryDrawingResultsConverter.toDto(entity);
    }

    public LotteryDrawingResult updateLotteryDrawingResult(Integer id, LotteryDrawingResult dto) {
        LotteryDrawingResultsEntity entity = em.find(LotteryDrawingResultsEntity.class, id);
        if (entity == null) {
            throw new NotFoundException("Drawing Result not found");
        }
        LotteryDrawingResultsEntity updatedEntity = LotteryDrawingResultsConverter.toEntity(dto);
        try {
            beginTx();
            updatedEntity.setId(entity.getId());
            updatedEntity = em.merge(updatedEntity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
            throw new RuntimeException("Error updating drawing result", e);
        }
        return LotteryDrawingResultsConverter.toDto(updatedEntity);
    }

    public boolean deleteLotteryDrawingResult(Integer id) {
        LotteryDrawingResultsEntity entity = em.find(LotteryDrawingResultsEntity.class, id);
        if (entity != null) {
            try {
                beginTx();
                em.remove(entity);
                commitTx();
                return true;
            } catch (Exception e) {
                rollbackTx();
                throw new RuntimeException("Error deleting drawing result", e);
            }
        } else {
            throw new NotFoundException("Drawing Result not found");
        }
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}