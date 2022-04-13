package com.panandafog.mt_server.music.services;

import com.panandafog.mt_server.authorisation.AppUser;
import com.panandafog.mt_server.authorisation.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationsService {

    private final UserService userService;

    @PersistenceContext
    EntityManager em;

    public List getAllOperations(HttpServletRequest req) {
        AppUser user = userService.whoami(req);

        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("get_all_operations");
        query.setParameter("selected_user_id", user.getId());
        query.execute();
        return query.getResultList();
    }
}
