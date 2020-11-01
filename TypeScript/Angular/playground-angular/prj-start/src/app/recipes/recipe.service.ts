import { Injectable } from '@angular/core';
import { Ingredient } from '../shared/ingredient.model';
import { Subject } from 'rxjs';
import { ShoppingListService } from '../shopping-list/shopping-list.service';
import { Recipe } from './recipe.model';
import { Store } from '@ngrx/store';
import * as ShoppingListActions from '../shopping-list/store/shopping-list.actions';
import * as fromApp from '../store/app.reducer';

@Injectable({ providedIn: 'root' })
export class RecipeService {

  recipesChanged = new Subject<Recipe[]>();

  private recipes: Recipe[] = [
    // new Recipe('recipe001', 'description001',
    //   'https://cdn.pixabay.com/photo/2016/06/15/19/09/food-1459693_640.jpg', [
    //   new Ingredient('Ingredient001', Math.ceil(Math.random() * 10)),
    //   new Ingredient('Ingredient002', Math.ceil(Math.random() * 10)),
    //   new Ingredient('Ingredient003', Math.ceil(Math.random() * 10))
    // ]),
    // new Recipe('recipe002', 'description002',
    //   'https://cdn.pixabay.com/photo/2016/01/14/17/46/eat-1140371__340.jpg', [
    //   new Ingredient('Ingredient005', Math.ceil(Math.random() * 10))
    // ]),
    // new Recipe('recipe003', 'description003', 'https://cdn.pixabay.com/photo/2016/01/14/17/46/eat-1140371__340.jpg', [])
  ];

  constructor(
    private store: Store<fromApp.AppState>
  ) { }

  setRecipes(recipes: Recipe[]) {
    this.recipes = recipes;
    this.recipesChanged.next(this.recipes.slice());
  }

  getRecipes() {
    return this.recipes.slice();
  }

  getRecipe(index: number) {
    return this.recipes[index];
  }

  onAddToShoppingList(ingredients: Ingredient[]) {
    this.store.dispatch(new ShoppingListActions.AddIngredients(ingredients));
  }

  addRecipe(recipes: Recipe) {
    this.recipes.push(recipes);
    this.recipesChanged.next(this.recipes.slice());
  }

  updateRecipe(index: number, newRecipe: Recipe) {
    this.recipes[index] = newRecipe;
    this.recipesChanged.next(this.recipes.slice());
  }

  deleteRecipe(index: number) {
    this.recipes.splice(index, 1);
    this.recipesChanged.next(this.recipes.slice());
  }

}
