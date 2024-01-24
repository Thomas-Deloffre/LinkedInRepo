package fr.isika.cdi07.dao;

import java.util.List;

import fr.isika.cdi07.model.projet.Article;

public interface ArticleRepository {
	boolean add(Article article);
	List<Article> all();
}
