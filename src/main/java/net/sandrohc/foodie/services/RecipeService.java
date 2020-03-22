/*
 * Copyright (c) 2020. Authored by SandroHc
 */

package net.sandrohc.foodie.services;

import net.sandrohc.foodie.model.Recipe;
import net.sandrohc.foodie.model.dto.RecipeSimple;
import net.sandrohc.foodie.repositories.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Service
@Component
public class RecipeService {

	private static final Logger LOG = LoggerFactory.getLogger(RecipeService.class);

	private final RecipeRepository repository;
	private final ReactiveMongoTemplate template;

	public RecipeService(RecipeRepository repository, ReactiveMongoTemplate template) {
		this.repository = repository;
		this.template = template;
	}

	public Mono<Recipe> set(Recipe recipe) {
		return repository.save(recipe);
	}

	/**
	 * Looks up a recipe with a given id.
	 *
	 * @param id The id to look the recipe up for.
	 * @return the requested {@link Recipe}.
	 */
	public Mono<Recipe> getById(Integer id) {
		LOG.info("Finding entity: {}", id);
		return repository.findById(id);
	}

	public Flux<Recipe> getByTitle(String title) {
		return repository.findAllByTitle(title);
	}

	public Flux<Recipe> getByIngredients(Collection<String> ingredient) {
		return repository.findAllByIngredients(ingredient);
	}

	public Mono<Page<RecipeSimple>> getAll(PageRequest page) {
		LOG.info("Find for: " + page);

		Query query = new Query().with(page);

		query.fields()
				.include("title")
				.include("description")
				.include("duration")
				.include("servings");

		return repository.count()
				.flatMap(count -> template.find(query, Recipe.class)
						.map(RecipeSimple::new)
						.collectList()
						.map(results -> new PageImpl<>(results, page, count)));
	}

	public Flux<Recipe> getRandom(int num) {
		return repository.findRandom(num);
	}

}
