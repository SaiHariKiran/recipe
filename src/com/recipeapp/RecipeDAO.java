package com.recipeapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {

    public void addRecipe(Recipe recipe) throws SQLException {
        String query = "INSERT INTO recipes (name, category, ingredients, instructions) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, recipe.getName());
            ps.setString(2, recipe.getCategory());
            ps.setString(3, recipe.getIngredients());
            ps.setString(4, recipe.getInstructions());
            ps.executeUpdate();
        }
    }

    public List<Recipe> getAllRecipes() throws SQLException {
        String query = "SELECT * FROM recipes";
        List<Recipe> recipes = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                recipes.add(new Recipe(
                        rs.getInt("recipe_id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("ingredients"),
                        rs.getString("instructions")
                ));
            }
        }
        return recipes;
    }

    // Additional methods for update, delete, and search can be added here.
}
