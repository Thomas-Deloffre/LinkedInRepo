package fr.isika.cdi07.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.projet.ProjetPage;
import fr.isika.cdi07.model.projet.ProjetSearchCriteria;

@Repository
public class ProjetCriteriaRepository {
	private final EntityManager entityManager;
	private final CriteriaBuilder criteriaBuilder;
	
	public ProjetCriteriaRepository(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
	}
	
	public Page<Projet> findAllWithFilters(ProjetPage projetPage, ProjetSearchCriteria projetSearchCriteria){
		CriteriaQuery<Projet> projetQuery = criteriaBuilder.createQuery(Projet.class);
		Root<Projet> projetRoot = projetQuery.from(Projet.class);
		Predicate predicate = getPredicate(projetSearchCriteria, projetRoot);
		projetQuery.where(predicate);
		setOrder(projetPage, projetQuery, projetRoot);
		TypedQuery<Projet> typedQuery = entityManager.createQuery(projetQuery);
		typedQuery.setFirstResult(projetPage.getPageNumber() * projetPage.getPageSize());
		typedQuery.setMaxResults(projetPage.getPageSize());
		Pageable pageable = getPageable(projetPage);
		long projetCount = getProjetCount(predicate);
		return new PageImpl<>(typedQuery.getResultList(), pageable, projetCount);
	}
	private long getProjetCount(Predicate predicate) {
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		Root<Projet> countRoot = countQuery.from(Projet.class);
		countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
		return entityManager.createQuery(countQuery).getSingleResult();
		}
	private Pageable getPageable(ProjetPage projetPage) {
		Sort sort = Sort.by(projetPage.getSortDirection(), projetPage.getSortBy());
		return PageRequest.of(projetPage.getPageNumber(), projetPage.getPageSize(), sort);
	}
	private void setOrder(ProjetPage projetPage, CriteriaQuery<Projet> projetQuery, Root<Projet> projetRoot) {
		if(projetPage.getSortDirection().equals(Sort.Direction.ASC)) {
			projetQuery.orderBy(criteriaBuilder.asc(projetRoot.get(projetPage.getSortBy())));
		} else {
			projetQuery.orderBy(criteriaBuilder.desc(projetRoot.get(projetPage.getSortBy())));
		}
		
	}
	private Predicate getPredicate(ProjetSearchCriteria projetSearchCriteria, Root<Projet> projetRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if(Objects.nonNull(projetSearchCriteria.getCategorie())) {
			predicates.add(
					criteriaBuilder.like(projetRoot.get("categorie"), "%" + projetSearchCriteria.getCategorie() +"%"));
		}
		if(Objects.nonNull(projetSearchCriteria.getGenre())) {
			predicates.add(
					criteriaBuilder.like(projetRoot.get("genre"), "%" + projetSearchCriteria.getGenre() +"%"));
		}
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}
}