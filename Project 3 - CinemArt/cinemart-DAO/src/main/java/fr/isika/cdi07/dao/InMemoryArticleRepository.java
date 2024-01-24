package fr.isika.cdi07.dao;

import java.util.ArrayList;
import java.util.List;

import fr.isika.cdi07.model.projet.Article;

public class InMemoryArticleRepository implements ArticleRepository{
	private List<Article> articles = new ArrayList<Article>();

	public InMemoryArticleRepository() {
	}

	@Override
	public boolean add(Article article) {
		this.articles.add(article);
		return true;
	}
	@Override
	public List<Article> all() {
		return articles;
	}
}