package fr.isika.cdi07.model.projet;

import org.springframework.data.domain.Sort;

public class ProjetPage {
	private int pageNumber = 0;
	private int pageSize = 12;
	private Sort.Direction sortDirection = Sort.Direction.ASC;
	private String sortBy = "categorie";
//	private String sortByCategorie = "categorie";
//	private String sortByGenre = "genre";
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Sort.Direction getSortDirection() {
		return sortDirection;
	}
	public void setSortDirection(Sort.Direction sortDirection) {
		this.sortDirection = sortDirection;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	
//	public String getSortByCategorie() {
//		return sortByCategorie;
//	}
//	public void setSortByCategorie(String sortByCategorie) {
//		this.sortByCategorie = sortByCategorie;
//	}
//	public String getSortByGenre() {
//		return sortByGenre;
//	}
//	public void setSortByGenre(String sortByGenre) {
//		this.sortByGenre = sortByGenre;
//	}
}