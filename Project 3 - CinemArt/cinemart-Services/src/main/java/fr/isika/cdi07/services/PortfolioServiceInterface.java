package fr.isika.cdi07.services;

import java.util.List;
import fr.isika.cdi07.model.utilisateur.PortFolio;

public interface PortfolioServiceInterface {
	List<PortFolio> getAllPortfolios();
	PortFolio getPortfolio(Long idPortfolio);
}