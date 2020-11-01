import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ShoppingEditComponent } from './shopping-edit/shopping-edit.component';
import { ShoppingListComponent } from './shopping-list.component';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    ShoppingListComponent,
    ShoppingEditComponent],
  imports: [
    FormsModule,
    RouterModule.forChild([
      { path: '', component: ShoppingListComponent }
    ]),
    SharedModule
  ],
  exports: [
    ShoppingListComponent,
    ShoppingEditComponent
  ]
})
export class ShoppingListModule {

}
