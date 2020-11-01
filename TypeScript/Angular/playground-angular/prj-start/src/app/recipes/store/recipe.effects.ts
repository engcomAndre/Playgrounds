import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, switchMap, withLatestFrom } from 'rxjs/operators';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { Recipe } from '../recipe.model';
import * as fromApp from '../../store/app.reducer';
import * as RecipeActions from '../store/recipe.action';
import { Store } from '@ngrx/store';

@Injectable({ providedIn: 'root' })
export class RecipeEffect {

  @Effect({ dispatch: false })
  fetchRecipes = this.actions$.pipe(
    ofType(RecipeActions.FETCH_RECIPES),
    switchMap(() => {
      return this.http.get<Recipe[]>(
        'https://ng-recipe-book-59ccf.firebaseio.com/recipes.json'
      );
    }),
    map(recipes => {
      return recipes.map(recipe => {
        return {
          ...recipe,
          ingredients: recipe.ingredients ? recipe.ingredients : []
        };
      });
    }),
    map(recipes => {
      return new RecipeActions.SetRecipes(recipes);
    })
  );

  @Effect({ dispatch: false })
  storeRecipes = this.actions$.pipe(
    ofType(RecipeActions.STORE_RECIPES),
    withLatestFrom(this.store.select('recipe')),
    switchMap(([actionData, recipesState]) => {
      return this.http.put(
        'https://ng-recipe-book-59ccf.firebaseio.com/recipes.json',
        recipesState.recipes
      );
    })
  );



  constructor(
    private actions$: Actions,
    private store: Store<fromApp.AppState>,
    private http: HttpClient) { }

}
