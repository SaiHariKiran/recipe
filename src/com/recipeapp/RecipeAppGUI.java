package com.recipeapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class RecipeAppGUI extends JFrame {
    private RecipeDAO recipeDAO = new RecipeDAO();
    private JTextField nameField, categoryField;
    private JTextArea ingredientsArea, instructionsArea;
    private JList<String> recipeList;

    public RecipeAppGUI() {
        // Setup the window (JFrame)
        setTitle("Smart Recipe System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Recipe Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        formPanel.add(categoryField);

        formPanel.add(new JLabel("Ingredients:"));
        ingredientsArea = new JTextArea();
        formPanel.add(new JScrollPane(ingredientsArea));

        formPanel.add(new JLabel("Instructions:"));
        instructionsArea = new JTextArea();
        formPanel.add(new JScrollPane(instructionsArea));

        JButton addButton = new JButton("Add Recipe");
        formPanel.add(addButton);

        add(formPanel, BorderLayout.NORTH);

        // Recipe List Panel
        recipeList = new JList<>();
        add(new JScrollPane(recipeList), BorderLayout.CENTER);

        // Add recipe to database when button is clicked
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    String category = categoryField.getText();
                    String ingredients = ingredientsArea.getText();
                    String instructions = instructionsArea.getText();

                    Recipe recipe = new Recipe(0, name, category, ingredients, instructions);
                    recipeDAO.addRecipe(recipe);
                    loadRecipes();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Load recipes from the database
        loadRecipes();

        // Set the GUI to be visible
        setVisible(true);
    }

    // Load recipes from the database
    private void loadRecipes() {
        try {
            List<Recipe> recipes = recipeDAO.getAllRecipes();
            DefaultListModel<String> model = new DefaultListModel<>();
            for (Recipe recipe : recipes) {
                model.addElement(recipe.getName());
            }
            recipeList.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
