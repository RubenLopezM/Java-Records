package com.example.springboot.BS3.Persona.infrastructure.Repository;

import com.example.springboot.BS3.Persona.domain.Persona;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PersonaRepositoryImpl{
@PersistenceContext
private EntityManager entityManager;

   public List<Persona> getData(HashMap<String, Object> conditions,int page) {
        {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Persona> query= cb.createQuery(Persona.class);
            Root<Persona> root = query.from(Persona.class);

            List<Predicate> predicates= new ArrayList<>();
            List<Order> orderList = new ArrayList();
            String sorted;
            conditions.forEach((field,value)->
            {
                switch (field)
                {
                    case "usuario":
                        predicates.add(cb.like(root.get(field),"%"+(String) value+"%"));
                        break;
                    case "name":
                        predicates.add(cb.like(root.get(field),"%"+(String) value+"%"));
                        break;
                    case "surname":
                        predicates.add(cb.equal(root.get(field),(String)value));
                        break;

                    case "created_date":
                        String dateCondition= (String) conditions.get("dateCondition");
                        switch (dateCondition)
                        {
                            case "greater":
                                predicates.add(cb.greaterThan(root.<Date>get(field),(Date) value));
                                break;
                            case "less":
                                predicates.add(cb.lessThan(root.<Date>get(field),(Date) value));
                                break;
                            case "equal":
                                predicates.add(cb.equal(root.<Date>get(field),(Date) value));
                                break;
                        }
                        break;
                }
            });
            sorted=(String) conditions.get("sorted");

           if (sorted!=null){
               if (sorted.equals("usuario")){
                   orderList.add(cb.asc(root.get("usuario")));
               }
               if (sorted.equals("name")){
                   orderList.add(cb.asc(root.get("name")));
               }
           }

            if (orderList.size()>0){
                query.select(root).where(predicates.toArray(new Predicate[predicates.size()])).orderBy(orderList);
            } else {
                query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
            }

            int pageNumber = page;
            int pageSize = 10;

            return entityManager.createQuery(query).setMaxResults(pageSize).setFirstResult((pageNumber+1)*pageSize-pageSize).getResultList();
        }
    }

}
