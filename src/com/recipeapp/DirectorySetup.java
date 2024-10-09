package com.recipeapp;

import java.io.File;

public class DirectorySetup {
    public static void main(String[] args) {
        File vegetarianDir = new File("Recipes/Vegetarian");
        File nonVegetarianDir = new File("Recipes/NonVegetarian");
        File dessertDir = new File("Recipes/Desserts");

        // Create directories if they don't exist
        if (!vegetarianDir.exists()) vegetarianDir.mkdirs();
        if (!nonVegetarianDir.exists()) nonVegetarianDir.mkdirs();
        if (!dessertDir.exists()) dessertDir.mkdirs();

        System.out.println("Directories created successfully!");
    }
}
