import { AuthEffects } from './auth/store/auth.effects';
import { CoreModule } from './coremodel.module';
import { SharedModule } from './shared/shared.module';
import { AppRoutingModule } from './app-routing-module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { StoreRouterConnectingModule } from '@ngrx/router-store';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { environment } from '../environments/environment';
import { RecipeEffect } from './recipes/store/recipe.effects';
import * as fromApp from './store/app.reducer';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule.withServerTransition({ appId: 'my-app' }),
    HttpClientModule,
    StoreModule.forRoot(fromApp.appReducer),
    EffectsModule.forRoot([AuthEffects, RecipeEffect]),
    AppRoutingModule,
    SharedModule,
    CoreModule,
    StoreDevtoolsModule.instrument({ logOnly: environment.production }),
    StoreRouterConnectingModule.forRoot()
  ],
  bootstrap: [AppComponent]

})
export class AppModule { }
