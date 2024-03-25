package fr.isika.cdi07.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import fr.isika.cdi07.dao.PorteFollioRepository;
import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.utilisateur.PortFolio;
import fr.isika.cdi07.model.utilisateur.Utilisateur;
@EntityScan(basePackages="fr.isika.cdi07.model.Portfolio")
@EnableJpaRepositories("fr.isika.cdi07.dao")
@Service

public class PortfolioService implements PortfolioServiceInterface{
	@Autowired
	private PorteFollioRepository portfolioRepository;
	public PortfolioService(PorteFollioRepository PortfolioRepository) {
		super();
		this.portfolioRepository = PortfolioRepository;
	}
	@Override
	public List<PortFolio> getAllPortfolios() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public PortFolio getPortfolio(Long idPortfolio) {
		// TODO Auto-generated method stub
		return null;
	}
	public PortFolio createPortfolio (Utilisateur utilisateur, List<Projet> listProj) {
		PortFolio Portfolio = new PortFolio(	
				utilisateur,				
				listProj);	
		portfolioRepository.save(Portfolio);
		return Portfolio;
	}
	public void deletePortfolio (Long idPortfolio) {


		portfolioRepository.deleteById(idPortfolio);
	}
	//	@Override
	//	public Long getProjet(Long idProjet) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}


}