package net.sandrohc.foodie.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class RecipeIngredient implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	public RecipeSubId id = new RecipeSubId();

	@MapsId("recipeId")
	@ManyToOne
	public Recipe recipe;

	@Column(nullable=false)
	private String name;

	@Embedded
	private Unit unit;

	@Column
	private String extra;

	@Column
	private String original;


	public RecipeIngredient() {
	}

	public RecipeIngredient(Recipe recipe, int id, String original, String name, Unit unit, String extra) {
		this.id.setId(id);
		this.recipe = recipe;
		this.original = original;
		this.name = name;
		this.unit = unit;
		this.extra = extra;
	}


	// <editor-fold defaultstate="collapsed" desc="Getters & Setters">

	public RecipeSubId getId() {
		return id;
	}

	public void setId(RecipeSubId id) {
		this.id = id;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	// </editor-fold>

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RecipeIngredient that = (RecipeIngredient) o;

		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "RecipeIngredient[" +
			   "id=" + id +
			   ", recipe=" + recipe +
			   ", value='" + original + '\'' +
			   ']';
	}
}