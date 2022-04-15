package com.panandafog.mt_server.music.services;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.authorisation.UserService;
import com.panandafog.mt_server.music.entities.shared.AddTracksOperationEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationsService {

    private final UserService userService;

    @PersistenceContext
    EntityManager em;

    public List<Object> getAllOperations(HttpServletRequest req) {
        AppUser user = userService.whoami(req);

        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("get_all_operations");
        query.setParameter("selected_user_id", 60);
        List<Object[]> resultList = query.unwrap(Query.class)
                .setResultTransformer(Transformers.aliasToBean(AddTracksOperationEntity.class)).getResultList();

        List<Object> objectsList = new ArrayList<Object>();

        for (Object[] line: resultList) {
            objectsList.add(line[0]);
        }

        return objectsList;
    }
}
