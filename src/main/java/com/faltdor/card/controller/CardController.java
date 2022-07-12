package com.faltdor.card.controller;

import com.faltdor.card.config.CardServiceConfig;
import com.faltdor.card.model.Cards;
import com.faltdor.card.model.Customer;
import com.faltdor.card.model.Properties;
import com.faltdor.card.repository.CardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Controller
@AllArgsConstructor
public class CardController {
    private final CardRepository cardRepository;

    private final CardServiceConfig cardsConfig;

    @PostMapping( "/myCards" )
    public List<Cards> getCardDetails( @RequestBody Customer customer ) {

        List<Cards> cards = cardRepository.findByCustomerId( customer.getCustomerId() );
        if ( cards != null ) {
            return cards;
        } else {
            return null;
        }
    }

    @GetMapping( "/cards/properties" )
    public String getPropertyDetails() throws JsonProcessingException {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties( cardsConfig.getMsg(), cardsConfig.getBuildVersion(),
              cardsConfig.getMailDetails(), cardsConfig.getActiveBranches() );
        String jsonStr = ow.writeValueAsString( properties );
        return jsonStr;
    }
}
