package fr.adriencaubel.jpa_spring_enterprise_architecture.commande;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.adriencaubel.jpa_spring_enterprise_architecture.article.Article;
import fr.adriencaubel.jpa_spring_enterprise_architecture.article.ArticleService;
import fr.adriencaubel.jpa_spring_enterprise_architecture.client.ClientRepository;
import fr.adriencaubel.jpa_spring_enterprise_architecture.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final ClientService clientService;
    private final ArticleService articleService;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository, ClientService clientService, ArticleService articleService) {
        this.commandeRepository = commandeRepository;
        this.clientService = clientService;
        this.articleService = articleService;
    }

    public List<Commande> getAllCommandes() {
        
    	return null;
    }
    
    public Commande createCommande(CommandeRequestModel commandeRequestModel) {
        List<Article> articles = commandeRequestModel.getArticleIds().stream().map(articleService::getById).collect(Collectors.toList());
        Commande commande = new Commande();
        commande.setArticles(articles);
        commande.setClient(clientService.getById(commandeRequestModel.getClientId()));
        commandeRepository.save(commande);
    	return commande;
    }

    public void deleteCommande(Long id) {
    	// todo
    }
}
